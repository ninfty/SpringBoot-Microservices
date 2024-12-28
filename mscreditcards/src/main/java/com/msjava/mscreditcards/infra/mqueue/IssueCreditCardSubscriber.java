package com.msjava.mscreditcards.infra.mqueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msjava.mscreditcards.domain.CreditCard;
import com.msjava.mscreditcards.domain.CustomerCreditCard;
import com.msjava.mscreditcards.domain.RequestCreditCardData;
import com.msjava.mscreditcards.infra.repository.CreditCardRepository;
import com.msjava.mscreditcards.infra.repository.CustomerCreditCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class IssueCreditCardSubscriber {

    private final CreditCardRepository creditCardRepository;
    private final CustomerCreditCardRepository customerCreditCardRepository;

    @RabbitListener(queues = "${mq.queues.issue-credit-cards}")
    public void receiveIssueRequest(@Payload String payload) {
        try {
            var mapper = new ObjectMapper();

            RequestCreditCardData data = mapper.readValue(payload, RequestCreditCardData.class);
            CreditCard creditCard = creditCardRepository.findById(data.getIdCreditCard()).orElseThrow();

            CustomerCreditCard customerCreditCard = new CustomerCreditCard();
            customerCreditCard.setCreditCard(creditCard);
            customerCreditCard.setDocument(data.getDocument());
            customerCreditCard.setLimit(data.getAvailableLimit());

            customerCreditCardRepository.save(customerCreditCard);

        } catch (Exception e) {
            log.error("Error when receiving card issuance request: {} ", e.getMessage());
        }
    }
}
