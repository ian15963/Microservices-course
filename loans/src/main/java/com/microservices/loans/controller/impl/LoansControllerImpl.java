package com.microservices.loans.controller.impl;

import com.microservices.loans.constants.LoansConstants;
import com.microservices.loans.controller.ILoansController;
import com.microservices.loans.dto.LoansDto;
import com.microservices.loans.dto.ResponseDto;
import com.microservices.loans.service.ILoansService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoansControllerImpl implements ILoansController {

    private final ILoansService service;

    @Override
    public ResponseEntity<ResponseDto> createLoan(String mobileNumber) {
        service.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Override
    public ResponseEntity<LoansDto> fetchLoan(String mobileNumber) {
        LoansDto dto = service.fetchLoan(mobileNumber);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ResponseDto> updateLoans(LoansDto loansDto) {
        boolean isUpdated = service.updateLoan(loansDto);
        if(isUpdated){
            return ResponseEntity.ok(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
    }

    @Override
    public ResponseEntity<ResponseDto> deleteLoan(String mobileNumber) {
        boolean isDeleted = service.deleteLoan(mobileNumber);
        if(isDeleted){
            return ResponseEntity.ok(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
    }
}
