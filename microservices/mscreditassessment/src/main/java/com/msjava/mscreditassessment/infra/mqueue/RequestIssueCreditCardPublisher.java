package com.msjava.mscreditassessment.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msjava.mscreditassessment.domain.model.RequestCreditCardData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestIssueCreditCardPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueIssueCreditCards;

    public void requestCreditCard(RequestCreditCardData data) throws JsonProcessingException {
        var json = convertToJson(data);

        rabbitTemplate.convertAndSend(queueIssueCreditCards.getName(), json);
    }

    private String convertToJson(RequestCreditCardData data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(data);
    }
}
