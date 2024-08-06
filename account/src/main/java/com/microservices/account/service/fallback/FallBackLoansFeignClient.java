package com.microservices.account.service.fallback;

import com.microservices.account.dto.LoansDto;
import com.microservices.account.service.client.LoansFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FallBackLoansFeignClient implements LoansFeignClient {

    @Override
    public ResponseEntity<LoansDto> fetchLoan(String correlationId, String mobileNumber) {
        return null;
    }

}
