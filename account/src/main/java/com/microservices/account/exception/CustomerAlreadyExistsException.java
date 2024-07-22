package com.microservices.account.exception;

public class CustomerAlreadyExistsException extends RuntimeException{

    public static final String MESSAGE = "Customer already exists";

    public CustomerAlreadyExistsException() {
        super(MESSAGE);
    }

    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
