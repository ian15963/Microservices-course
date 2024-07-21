package com.microservices.loans.service.impl;

import com.microservices.loans.constants.LoansConstants;
import com.microservices.loans.dto.LoansDto;
import com.microservices.loans.entity.LoansEntity;
import com.microservices.loans.exception.LoansAlreadyExistsException;
import com.microservices.loans.exception.ResourceNotFoundException;
import com.microservices.loans.mapper.LoansMapper;
import com.microservices.loans.repository.LoansRepository;
import com.microservices.loans.service.ILoansService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private final LoansRepository repository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<LoansEntity> optionalEntity = repository.findByMobileNumber(mobileNumber);
        if(optionalEntity.isPresent()){
            throw new LoansAlreadyExistsException();
        }
        repository.save(createNewLoan(mobileNumber));
    }

    @Override
    public LoansEntity createNewLoan(String mobileNumber) {
        LoansEntity newLoan = new LoansEntity();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        LoansEntity entity = repository.findByMobileNumber(mobileNumber).orElseThrow(ResourceNotFoundException::new);
        return LoansMapper.toDto(entity);
    }

    @Override
    public boolean updateLoan(LoansDto dto) {
        LoansEntity loan = repository.findByLoanNumber(dto.getLoanNumber()).orElseThrow(ResourceNotFoundException::new);
        LoansEntity updatedLoan = LoansMapper.toEntity(dto);
        updatedLoan.setLoanId(loan.getLoanId());
        repository.save(updatedLoan);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        LoansEntity loan = repository.findByMobileNumber(mobileNumber).orElseThrow(ResourceNotFoundException::new);
        repository.deleteById(loan.getLoanId());
        return true;
    }
}
