package com.example.rba.exception;

public class ActiveClientCardRequestException extends RuntimeException {

    public ActiveClientCardRequestException(String taxNumber) {
        super("Client with tax number " + taxNumber + " already has an active card request" );
    }
}
