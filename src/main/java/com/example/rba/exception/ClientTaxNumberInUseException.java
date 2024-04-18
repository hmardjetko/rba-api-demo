package com.example.rba.exception;

public class ClientTaxNumberInUseException extends RuntimeException {

    public ClientTaxNumberInUseException(String taxNumber) {
        super("Client with tax number " + taxNumber + " already exists");
    }
}
