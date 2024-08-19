package com.microservices.account.service.impl;

import com.microservices.account.constant.AccountConstant;
import com.microservices.account.dto.AccountMessageDto;
import com.microservices.account.dto.CustomerDto;
import com.microservices.account.entity.AccountEntity;
import com.microservices.account.entity.CustomerEntity;
import com.microservices.account.exception.CustomerAlreadyExistsException;
import com.microservices.account.exception.ResourceNotFoundException;
import com.microservices.account.mapper.AccountMapper;
import com.microservices.account.mapper.CustomerMapper;
import com.microservices.account.repository.AccountRepository;
import com.microservices.account.repository.CustomerRepository;
import com.microservices.account.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final StreamBridge streamBridge;
    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<CustomerEntity> entity = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(entity.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exists with the given number %s".formatted(customerDto.getMobileNumber()));
        }
        CustomerEntity newCustomer = CustomerMapper.toEntity(customerDto);
        newCustomer = customerRepository.save(newCustomer);
        AccountEntity newAccount = accountRepository.save(createNewAccount(newCustomer));
        sendCommunication(newAccount, newCustomer);
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        CustomerEntity customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(ResourceNotFoundException::new);
        AccountEntity account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(ResourceNotFoundException::new);
        return CustomerMapper.toDto(customer, AccountMapper.toDto(account));
    }

    @Override
    public AccountEntity createNewAccount(CustomerEntity customerEntity) {
        int randomAccNumber = new Random().nextInt(900000000);
        return AccountEntity.builder()
                .customerId(customerEntity.getCustomerId())
                .accountNumber(randomAccNumber)
                .accountType(AccountConstant.SAVINGS)
                .branchAddress(AccountConstant.ADDRESS)
                .build();
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        if(customerDto.getAccountsDto() != null){
            CustomerEntity cutomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber()).orElseThrow(ResourceNotFoundException::new);
            CustomerEntity updatedCustomer = CustomerMapper.toEntity(customerDto);
            updatedCustomer.setCustomerId(cutomer.getCustomerId());
            customerRepository.save(updatedCustomer);

            AccountEntity account = accountRepository.findByCustomerId(customerDto.getAccountsDto().getAccountNumber()).orElseThrow(ResourceNotFoundException::new);
            AccountEntity updatedAccount = AccountMapper.toEntity(customerDto.getAccountsDto());
            updatedAccount.setAccountNumber(account.getAccountNumber());
            accountRepository.save(updatedAccount);
            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        CustomerEntity cutomer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(ResourceNotFoundException::new);
        customerRepository.deleteById(cutomer.getCustomerId());
        return true;
    }

    @Override
    public boolean updateCommunicationStatus(Integer accountNumber) {
        boolean isUpdated = false;
        if(accountNumber != null){
            AccountEntity account = accountRepository.findById(accountNumber).orElseThrow(ResourceNotFoundException::new);
            account.setCommunicationSw(true);
            accountRepository.save(account);
            isUpdated = true;
        }
        return isUpdated;
    }

    private void sendCommunication(AccountEntity account, CustomerEntity customer) {
        var accountsMsgDto = new AccountMessageDto(account.getAccountNumber(), customer.getName(),
                customer.getEmail(), customer.getMobileNumber());
        log.info("Sending Communication request for the details: {}", accountsMsgDto);
        var result = streamBridge.send("sendCommunication-out-0", accountsMsgDto);
        log.info("Is the Communication request successfully triggered ? : {}", result);
    }

}
