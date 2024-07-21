package com.microservices.card.exception;

public class ResourceNotFoundException extends RuntimeException{

    public static final String MESSAGE = "Card does not exists!";

    public ResourceNotFoundException() {
        super(MESSAGE);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
