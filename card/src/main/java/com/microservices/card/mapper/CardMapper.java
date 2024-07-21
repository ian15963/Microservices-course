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
        CardEntity entity = new CardEntity();
        entity.setAmountUsed(dto.getAmountUsed());
        entity.setCardType(dto.getCardType());
        entity.setCardNumber(dto.getCardNumber());
        entity.setMobileNumber(dto.getMobileNumber());
        entity.setTotalLimit(dto.getTotalLimit());
        entity.setAvailableAmount(dto.getAvailableAmount());
        return entity;
    }
}
