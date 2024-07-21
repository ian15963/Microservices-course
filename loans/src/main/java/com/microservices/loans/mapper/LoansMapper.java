package com.microservices.loans.mapper;

import com.microservices.loans.dto.LoansDto;
import com.microservices.loans.entity.LoansEntity;

public class LoansMapper {

    public static LoansEntity toEntity(LoansDto dto){
        LoansEntity entity = new LoansEntity();
        entity.setAmountPaid(dto.getAmountPaid());
        entity.setLoanNumber(dto.getLoanNumber());
        entity.setLoanType(dto.getLoanType());
        entity.setTotalLoan(dto.getTotalLoan());
        entity.setMobileNumber(dto.getMobileNumber());
        entity.setOutstandingAmount(dto.getOutstandingAmount());
        return entity;
    }

    public static LoansDto toDto(LoansEntity entity){
        LoansDto dto = new LoansDto();
        dto.setAmountPaid(entity.getAmountPaid());
        dto.setLoanNumber(entity.getLoanNumber());
        dto.setLoanType(entity.getLoanType());
        dto.setTotalLoan(entity.getTotalLoan());
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setOutstandingAmount(entity.getOutstandingAmount());
        return dto;
    }
}
