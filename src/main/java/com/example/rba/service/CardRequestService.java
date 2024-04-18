package com.example.rba.service;

import com.example.rba.exception.ActiveClientCardRequestException;
import com.example.rba.model.CardRequest;
import com.example.rba.model.CardRequestStatusUpdateRequest;
import com.example.rba.model.Client;
import com.example.rba.model.NewCardRequest;
import com.example.rba.model.enums.CardRequestStatus;
import com.example.rba.model.enums.ClientStatus;
import com.example.rba.repository.CardRequestRepository;
import com.example.rba.repository.ClientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardRequestService {

    Logger logger = LoggerFactory.getLogger(CardRequestService.class);

    CardRequestRepository cardRequestRepository;
    ClientService clientService;
    ClientRepository clientRepository;
    KafkaProducerService kafkaProducerService;

    public CardRequestService(CardRequestRepository cardRequestRepository, ClientService clientService, ClientRepository clientRepository,
                              KafkaProducerService kafkaProducerService) {
        this.cardRequestRepository = cardRequestRepository;
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public CardRequest createCardRequest(NewCardRequest newCardRequest) {
        Client client = fetchOrCreateClient(newCardRequest);
        List<CardRequestStatus> activeRequestStatuses = Arrays.asList(CardRequestStatus.IN_PROGRESS, CardRequestStatus.PENDING_PROCESSING);
        List<CardRequest> activeCardRequestList = cardRequestRepository.findByClientId(client.getId())
                .stream().filter(cr -> CardRequestStatus.UNKNOWN.equals(cr.getCardRequestStatus())
                        || activeRequestStatuses.contains(cr.getCardRequestStatus()))
                .collect(Collectors.toUnmodifiableList());

        if (!activeCardRequestList.isEmpty()) {
            throw new ActiveClientCardRequestException(newCardRequest.getOib());
        }

        CardRequest cardRequest = new CardRequest();
        cardRequest.setClient(client);
        cardRequest.setCardRequestStatus(CardRequestStatus.PENDING_PROCESSING);
        cardRequest.setCreated(LocalDateTime.now());
        cardRequest = cardRequestRepository.save(cardRequest);

        return cardRequest;
    }

    private Client fetchOrCreateClient(NewCardRequest newCardRequest) {
        Client existingClient = clientRepository.findByTaxNumber(newCardRequest.getOib()).orElse(null);

        if (existingClient != null) {
            return existingClient;
        } else {
            Client newClient = new Client();
            newClient.setFirstName(newCardRequest.getFirstName());
            newClient.setSurname(newCardRequest.getLastName());
            newClient.setTaxNumber(newCardRequest.getOib());
            newClient.setClientStatus(ClientStatus.ACTIVE);
            return clientService.save(newClient);
        }
    }

    public void sendCardRequestStatusUpdateMessageToKafkaTopic(CardRequestStatusUpdateRequest cardRequestStatusUpdate) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            kafkaProducerService.sendMessage(objectMapper.writeValueAsString(cardRequestStatusUpdate));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void processCardRequestStatusUpdateMessage(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            CardRequestStatusUpdateRequest cardRequestStatusUpdate = mapper.readValue(message, CardRequestStatusUpdateRequest.class);
            CardRequest cardRequest = cardRequestRepository.findById(Long.valueOf(cardRequestStatusUpdate.getRequestId())).orElse(null);
            if (cardRequest == null) {
                logger.error("Card request with ID: {} doesn't exist", cardRequestStatusUpdate.getRequestId() );
            } else {
                cardRequest.setDescription(cardRequestStatusUpdate.getDescription());
                cardRequest.setUpdated(LocalDateTime.now());
                cardRequest.setCardRequestStatus(CardRequestStatus.mapStringToValue(cardRequestStatusUpdate.getStatus()));
                if (CardRequestStatus.UNKNOWN.equals(cardRequest.getCardRequestStatus())) {
                    cardRequest.setErrorMessage("Couldn't map card request status from value: " + cardRequestStatusUpdate.getStatus());
                }
                cardRequestRepository.save(cardRequest);
            }
        } catch (JsonProcessingException e) {
            logger.error("Error parsing JSON message: {}", e.getMessage());
        } catch (NumberFormatException e) {
            logger.error("Error parsing number: {}", e.getMessage());
        }
    }
}
