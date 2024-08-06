package com.microservices.card.controller.impl;

import com.microservices.card.constants.CardConstants;
import com.microservices.card.controller.ICardController;
import com.microservices.card.dto.CardContactInfoDto;
import com.microservices.card.dto.CardDto;
import com.microservices.card.dto.ResponseDto;
import com.microservices.card.service.ICardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CardControllerImpl implements ICardController {

    private final ICardService service;
    private final Environment enviroment;
    private final CardContactInfoDto contactInfoDto;
    private final Logger logger = LoggerFactory.getLogger(CardControllerImpl.class);

    @Value("${build.version}")
    private String buildVersion;

    @Override
    public ResponseEntity<ResponseDto> createCard(CardDto dto) {
        service.create(dto.getMobileNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    @Override
    public ResponseEntity<CardDto> fetchCard(String correlationId, String mobileNumber) {
        CardDto card = service.fetch(mobileNumber);
        logger.debug("eazyBank-correlation-id found: {} ", correlationId);
        return ResponseEntity.ok(card);
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

    @Override
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.ok(buildVersion);
    }

    @Override
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.ok(enviroment.getProperty("JAVA_HOME"));
    }

    @Override
    public ResponseEntity<CardContactInfoDto> getContactInfo() {
        return ResponseEntity.ok(contactInfoDto);
    }
}
