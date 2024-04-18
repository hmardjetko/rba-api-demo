package com.example.rba.service;

import com.example.rba.exception.ClientTaxNumberInUseException;
import com.example.rba.model.Client;
import com.example.rba.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client newClient) {
        if (clientRepository.findByTaxNumber(newClient.getTaxNumber()).isPresent()) {
            throw new ClientTaxNumberInUseException(newClient.getTaxNumber());
        }
        return clientRepository.save(newClient);
    }
}
