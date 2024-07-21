package com.microservices.card.repository;

import com.microservices.card.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Integer> {

    Optional<CardEntity> findByMobileNumber(String mobileNumber);
    Optional<CardEntity> findByCardNumber(String cardNumber);
}
