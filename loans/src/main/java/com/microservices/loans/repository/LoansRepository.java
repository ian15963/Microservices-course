package com.microservices.loans.repository;

import com.microservices.loans.entity.LoansEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoansRepository extends JpaRepository<LoansEntity, Integer> {

    Optional<LoansEntity> findByMobileNumber(String mobileNumber);
    Optional<LoansEntity> findByLoanNumber(String loanNumber);
}
