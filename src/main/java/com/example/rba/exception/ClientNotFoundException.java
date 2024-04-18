package com.example.rba.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String taxNumber) {
        super("Client with taxNumber " + taxNumber + " not found");
    }
}
