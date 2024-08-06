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
import com.microservices.account.service.CardFeignClient;
import com.microservices.account.service.ICustomerService;
import com.microservices.account.service.LoansFeignClient;
import lombok.RequiredArgsConstructor;
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
        CardDto cardDto = cardFeignClient.fetchCard(correlationId, mobileNumber).getBody();
        LoansDto loansDto = loansFeignClient.fetchLoan(correlationId, mobileNumber).getBody();
        AccountDto accountDto = AccountMapper.toDto(accountEntity);
        return CustomerMapper.toDto(entity, accountDto, loansDto, cardDto);
    }
}
