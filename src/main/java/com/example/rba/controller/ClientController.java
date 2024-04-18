package com.example.rba.controller;

import com.example.rba.exception.ClientNotFoundException;
import com.example.rba.model.Client;
import com.example.rba.repository.ClientRepository;
import com.example.rba.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientRepository clientRepository;
    private final ClientService clientService;

    ClientController(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getClients(@RequestParam(required = false) String taxNumber) {
        if(StringUtils.hasText(taxNumber)) {
            List<Client> clients = new ArrayList<>();
            clientRepository.findByTaxNumber(taxNumber).map(c -> clients.add(c));
            return ResponseEntity.ok(clients);
        }
        else {
            return ResponseEntity.ok(clientRepository.findAll());
        }
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> fetchClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientRepository.findById(id).orElse(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client newClient) {
        return clientRepository.findById(id)
                .map(client -> {
                  client.setFirstName(newClient.getFirstName());
                  client.setSurname(newClient.getSurname());
                  client.setTaxNumber(newClient.getTaxNumber());
                  client.setClientStatus(newClient.getClientStatus());
                  return new ResponseEntity<>(clientRepository.save(client), HttpStatus.CREATED);
                })
            .orElseGet(() -> {
                newClient.setId(id);
                return new ResponseEntity<>(clientService.save(newClient), HttpStatus.CREATED);
            });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteByTaxNumber")
    public ResponseEntity deleteClientByTaxNumber(@RequestParam String taxNumber) {
        Optional<Client> client = clientRepository.findByTaxNumber(taxNumber);
        if (client.isPresent()) {
            clientRepository.delete(client.get());
            return ResponseEntity.noContent().build();
        } else {
            throw new ClientNotFoundException(taxNumber);
        }
    }
}
