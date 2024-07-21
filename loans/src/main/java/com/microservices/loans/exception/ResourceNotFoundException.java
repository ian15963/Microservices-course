package com.microservices.loans.exception;

public class ResourceNotFoundException extends RuntimeException{

    public static final String MESSAGE = "Loan not found";

    public ResourceNotFoundException(){
        super(MESSAGE);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
