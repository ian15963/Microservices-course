package com.microservices.card.exception;

public class CardAlreadyExistsException extends RuntimeException{

    public static final String MESSAGE = "Card already exists";

    public CardAlreadyExistsException() {
        super(MESSAGE);
    }

    public CardAlreadyExistsException(String message) {
        super(message);
    }
}
