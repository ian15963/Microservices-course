package com.microservices.account.controller.impl;

import com.microservices.account.constant.AccountConstant;
import com.microservices.account.controller.IAccountController;
import com.microservices.account.dto.AccountContactInfoDto;
import com.microservices.account.dto.CustomerDto;
import com.microservices.account.dto.ResponseDto;
import com.microservices.account.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountControllerImpl implements IAccountController {

    private final IAccountService service;
    private final Environment environment;
    private final AccountContactInfoDto contactInfoDto;

    @Value("${build.version}")
    private String buildVersion;

    @Override
    public ResponseEntity<ResponseDto> create(CustomerDto dto) {
        service.createAccount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
    }

    @Override
    public ResponseEntity<CustomerDto> fetch(String mobileNumber) {
        CustomerDto customerDto = service.fetchAccount(mobileNumber);
        return ResponseEntity.ok(customerDto);
    }

    @Override
    public ResponseEntity<ResponseDto> update(CustomerDto dto) {
        boolean isUpdated = service.updateAccount(dto);
        if(isUpdated){
            return ResponseEntity.ok(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountConstant.STATUS_417, AccountConstant.MESSAGE_417_UPDATE));
    }

    @Override
    public ResponseEntity<ResponseDto> delete(String mobileNumber) {
        boolean isDeleted = service.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity.ok(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountConstant.STATUS_417, AccountConstant.MESSAGE_417_DELETE));
    }

    @Override
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.ok(buildVersion);
    }

    @Override
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.ok(environment.getProperty("JAVA_HOME"));
    }

    @Override
    public ResponseEntity<AccountContactInfoDto> getContactInfo() {
        return ResponseEntity.ok(contactInfoDto);
    }


}
