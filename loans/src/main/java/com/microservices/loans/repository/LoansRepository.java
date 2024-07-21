package com.microservices.loans.repository;

import com.microservices.loans.entity.LoansEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoansRepository extends JpaRepository<LoansEntity, Integer> {
}
