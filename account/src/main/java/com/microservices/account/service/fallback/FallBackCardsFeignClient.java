package com.microservices.account.service.fallback;

import com.microservices.account.dto.CardDto;
import com.microservices.account.service.client.CardFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FallBackCardsFeignClient implements CardFeignClient {

    @Override
    public ResponseEntity<CardDto> fetchCard(String correlationId, String mobileNumber) {
        return null;
    }
}
