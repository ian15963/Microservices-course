package com.microservices.loans.mapper;

import com.microservices.loans.dto.LoansDto;
import com.microservices.loans.entity.LoansEntity;

public class LoansMapper {

    public static LoansEntity toEntity(LoansDto dto){
        return LoansEntity.builder()
                .amountPaid(dto.getAmountPaid())
                .loanNumber(dto.getLoanNumber())
                .loanType(dto.getLoanType())
                .totalLoan(dto.getTotalLoan())
                .mobileNumber(dto.getMobileNumber())
                .outstandingAmount(dto.getOutstandingAmount())
                .build();
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
