package com.microservices.card.controller.impl;

import com.microservices.card.constants.CardConstants;
import com.microservices.card.controller.ICardController;
import com.microservices.card.dto.CardDto;
import com.microservices.card.dto.ResponseDto;
import com.microservices.card.entity.CardEntity;
import com.microservices.card.service.impl.CardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CardControllerImpl implements ICardController {

    private final CardServiceImpl service;

    @Override
    public ResponseEntity<ResponseDto> createCard(CardDto dto) {
        service.create(dto.getMobileNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    @Override
    public ResponseEntity<ResponseDto> fetchCard(String mobileNumber) {
        CardDto card = service.fetch(mobileNumber);
        return ResponseEntity.ok(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
    }

    @Override
    public ResponseEntity<ResponseDto> update(CardDto dto) {
        boolean isUpdate = service.update(dto);
        if(isUpdate){
            return ResponseEntity.ok(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
    }

    @Override
    public ResponseEntity<ResponseDto> delete(String mobileNumber) {
        boolean isDeleted = service.delete(mobileNumber);
        if(isDeleted){
            return ResponseEntity.ok(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));

    }
}
