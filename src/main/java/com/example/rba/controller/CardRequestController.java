package com.example.rba.controller;

import com.example.rba.model.CardRequest;
import com.example.rba.model.CardRequestStatusUpdateRequest;
import com.example.rba.model.NewCardRequest;
import com.example.rba.model.Response;
import com.example.rba.model.enums.CardRequestStatus;
import com.example.rba.repository.CardRequestRepository;
import com.example.rba.service.CardRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CardRequestController {

    private final CardRequestRepository cardRequestRepository;
    private final CardRequestService cardRequestService;

    CardRequestController(CardRequestRepository cardRequestRepository, CardRequestService cardRequestService) {
        this.cardRequestRepository = cardRequestRepository;
        this.cardRequestService = cardRequestService;
    }

    @GetMapping("/cardRequests")
    public ResponseEntity<List<CardRequest>> getAllCardRequests(@RequestParam(required = false) Long idClient) {
        if(idClient != null) {
            return ResponseEntity.ok(cardRequestRepository.findByClientId(idClient));
        } else {
            return ResponseEntity.ok(cardRequestRepository.findAll());
        }
    }

    @PostMapping("/card-request")
    public ResponseEntity<Response> createCardRequest(@Valid @RequestBody NewCardRequest newCardRequest)  {
        cardRequestService.createCardRequest(newCardRequest);
        Response response = new Response();
        response.setMessage("New card request successfully created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/cardRequestStatusUpdate")
    public ResponseEntity<Response> updateCardRequestStatus(@Valid @RequestBody CardRequestStatusUpdateRequest cardRequestStatusUpdate) {
        cardRequestService.sendCardRequestStatusUpdateMessageToKafkaTopic(cardRequestStatusUpdate);
        Response response = new Response();
        response.setMessage("Card request status update sent");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
