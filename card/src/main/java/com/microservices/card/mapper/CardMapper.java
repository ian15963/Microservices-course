package com.microservices.card.mapper;

import com.microservices.card.dto.CardDto;
import com.microservices.card.entity.CardEntity;

public class CardMapper {

    public static CardDto toDto(CardEntity entity){
        CardDto dto = new CardDto();
        dto.setCardNumber(entity.getCardNumber());
        dto.setCardType(entity.getCardType());
        dto.setAmountUsed(entity.getAmountUsed());
        dto.setTotalLimit(entity.getTotalLimit());
        dto.setAvailableAmount(entity.getAvailableAmount());
        dto.setMobileNumber(entity.getMobileNumber());
        return dto;
    }

    public static CardEntity toEntity(CardDto dto){
        return CardEntity.builder()
                .amountUsed(dto.getAmountUsed())
                .cardType(dto.getCardType())
                .cardNumber(dto.getCardNumber())
                .mobileNumber(dto.getMobileNumber())
                .totalLimit(dto.getTotalLimit())
                .availableAmount(dto.getAvailableAmount())
                .build();
    }
}
