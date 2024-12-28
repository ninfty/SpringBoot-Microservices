package com.msjava.mscreditassessment.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.issue-credit-cards}")
    private String issueCreditCardsQueue;

    @Bean
    public Queue queueIssueCreditCards() {
        return new Queue(issueCreditCardsQueue, true);
    }
}
