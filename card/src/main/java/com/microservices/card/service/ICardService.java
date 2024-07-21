package com.microservices.card.service;

import com.microservices.card.dto.CardDto;
import com.microservices.card.entity.CardEntity;

public interface ICardService {

    void create(String mobileNumber);

    CardEntity createNewCard(String mobileNumber);

    CardDto fetch(String mobileNumber);

    boolean delete(String mobileNumber);

    boolean update(CardDto cardDto);
}
