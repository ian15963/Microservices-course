package com.microservices.account.service;

import com.microservices.account.dto.CustomerDto;
import com.microservices.account.entity.AccountEntity;
import com.microservices.account.entity.CustomerEntity;

public interface IAccountService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    AccountEntity createNewAccount(CustomerEntity customerEntity);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);

    boolean updateCommunicationStatus(Integer accountNumber);
}
