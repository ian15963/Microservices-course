package com.microservices.account.mapper;

import com.microservices.account.dto.AccountDto;
import com.microservices.account.dto.CustomerDto;
import com.microservices.account.entity.CustomerEntity;

public class CustomerMapper {

    public static CustomerEntity toEntity(CustomerDto dto){
        return CustomerEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .mobileNumber(dto.getMobileNumber())
                .build();
    }

    public static CustomerDto toDto(CustomerEntity entity, AccountDto accountDto){
        CustomerDto dto = new CustomerDto();
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setAccountsDto(accountDto);
        return dto;
    }

}
