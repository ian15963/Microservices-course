package com.microservices.loans.service;

import com.microservices.loans.dto.LoansDto;
import com.microservices.loans.entity.LoansEntity;

public interface ILoansService {

    void createLoan(String mobileNumber);

    LoansEntity createNewLoan(String mobileNumber);

    LoansDto fetchLoan(String mobileNumber);

    boolean updateLoan(LoansDto dto);

    boolean deleteLoan(String mobileNumber);
}
