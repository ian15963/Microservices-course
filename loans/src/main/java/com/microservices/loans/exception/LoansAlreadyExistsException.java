package com.microservices.loans.exception;

public class LoansAlreadyExistsException extends RuntimeException{

    public static final String MESSAGE = "Loan already exists";

    public LoansAlreadyExistsException(){
        super(MESSAGE);
    }

    public LoansAlreadyExistsException(String message) {
        super(message);
    }
}
