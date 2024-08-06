package com.microservices.account.service.impl;

import com.microservices.account.dto.AccountDto;
import com.microservices.account.dto.CardDto;
import com.microservices.account.dto.CustomerDto;
import com.microservices.account.dto.LoansDto;
import com.microservices.account.entity.AccountEntity;
import com.microservices.account.entity.CustomerEntity;
import com.microservices.account.exception.ResourceNotFoundException;
import com.microservices.account.mapper.AccountMapper;
import com.microservices.account.mapper.CustomerMapper;
import com.microservices.account.repository.AccountRepository;
import com.microservices.account.repository.CustomerRepository;
import com.microservices.account.service.client.CardFeignClient;
import com.microservices.account.service.ICustomerService;
import com.microservices.account.service.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final CardFeignClient cardFeignClient;
    private final LoansFeignClient loansFeignClient;

    @Override
    public CustomerDto fetchCustomerDtoDetails(String correlationId, String mobileNumber) {
        CustomerEntity entity = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(ResourceNotFoundException::new);
        AccountEntity accountEntity = accountRepository.findByCustomerId(entity.getCustomerId()).orElseThrow(ResourceNotFoundException::new);
        ResponseEntity<CardDto> cardResponse = cardFeignClient.fetchCard(correlationId, mobileNumber);
        CardDto cardDto = null;
        LoansDto loansDto = null;
        if(null != cardResponse){
            cardDto = cardResponse.getBody();
        }
        ResponseEntity<LoansDto> loansResponse = loansFeignClient.fetchLoan(correlationId, mobileNumber);
        if(null != loansResponse){
            loansDto = loansResponse.getBody();
        }
        AccountDto accountDto = AccountMapper.toDto(accountEntity);
        return CustomerMapper.toDto(entity, accountDto, loansDto, cardDto);
    }
}
