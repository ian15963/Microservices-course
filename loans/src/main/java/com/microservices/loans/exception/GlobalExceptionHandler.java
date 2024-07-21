package com.microservices.loans.exception;

import com.microservices.loans.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> resourceNotFound(ResourceNotFoundException e, WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorCode(HttpStatus.NOT_FOUND);
        errorResponseDto.setErrorTime(LocalDateTime.now());
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    @ExceptionHandler(LoansAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> loansAlreadyExists(LoansAlreadyExistsException e, WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorMessage(e.getMessage());
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setErrorTime(LocalDateTime.now());
        errorResponseDto.setErrorCode(HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

}
