package com.microservices.card.service.impl;

import com.microservices.card.constants.CardConstants;
import com.microservices.card.dto.CardDto;
import com.microservices.card.entity.CardEntity;
import com.microservices.card.exception.CardAlreadyExistsException;
import com.microservices.card.exception.ResourceNotFoundException;
import com.microservices.card.mapper.CardMapper;
import com.microservices.card.repository.CardRepository;
import com.microservices.card.service.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements ICardService {

    private final CardRepository repository;

    @Override
    public void create(String mobileNumber) {
        Optional<CardEntity> card = repository.findByMobileNumber(mobileNumber);
        if(card.isPresent()){
            throw new CardAlreadyExistsException();
        }
        CardEntity cardEntity = createNewCard(mobileNumber);
        repository.save(cardEntity);
    }

    @Override
    public CardEntity createNewCard(String mobileNumber) {
        CardEntity newCard = new CardEntity();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardDto fetch(String mobileNumber) {
        CardEntity card = repository.findByMobileNumber(mobileNumber).orElseThrow(ResourceNotFoundException::new);
        return CardMapper.toDto(card);
    }

    @Override
    public boolean delete(String mobileNumber) {
        CardEntity card = repository.findByMobileNumber(mobileNumber).orElseThrow(ResourceNotFoundException::new);
        repository.deleteById(card.getCardId());
        return true;
    }

    @Override
    public boolean update(CardDto cardDto) {
        CardEntity card = repository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(ResourceNotFoundException::new);
        CardEntity entity = CardMapper.toEntity(cardDto);
        entity.setCardId(card.getCardId());
        repository.save(card);
        return true;
    }
}
