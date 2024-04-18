package com.example.rba.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value(value = "${message.topic.name}")
    private String topicName;

    public void sendMessage(String message) {

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.whenComplete((result, ex) -> {

            if (ex == null) {
                logger.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata()
                        .offset());
            } else {
                logger.error("Unable to send message=[{}] due to : {}", message, ex.getMessage());
            }
        });
    }

}
