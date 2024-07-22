package com.microservices.account.mapper;

import com.microservices.account.dto.AccountDto;
import com.microservices.account.entity.AccountEntity;

public class AccountMapper {

    public static AccountEntity toEntity(AccountDto dto){
        return AccountEntity.builder()
                .accountNumber(dto.getAccountNumber())
                .branchAddress(dto.getBranchAddress())
                .accountType(dto.getAccountType())
                .build();
    }

    public static AccountDto toDto(AccountEntity entity){
        AccountDto dto = new AccountDto();
        dto.setAccountNumber(entity.getAccountNumber());
        dto.setAccountType(entity.getAccountType());
        dto.setBranchAddress(entity.getBranchAddress());
        return dto;
    }
}
