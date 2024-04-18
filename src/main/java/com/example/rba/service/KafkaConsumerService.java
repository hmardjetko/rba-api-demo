package com.example.rba.service;

import com.example.rba.repository.CardRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    CardRequestService cardRequestService;
    CardRequestRepository cardRequestRepository;

    public KafkaConsumerService(CardRequestService cardRequestService, CardRequestRepository cardRequestRepository) {
        this.cardRequestService = cardRequestService;
        this.cardRequestRepository = cardRequestRepository;
    }

    @KafkaListener(topics = "${message.topic.name}", groupId = "card-request", containerFactory = "cardRequestKafkaListenerContainerFactory")
    public void consume(String message) {
        logger.info("Received Message in group 'card-request': {}", message);
        cardRequestService.processCardRequestStatusUpdateMessage(message);
    }

}
