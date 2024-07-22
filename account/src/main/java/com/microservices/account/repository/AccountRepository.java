package com.microservices.account.repository;

import com.microservices.account.entity.AccountEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    Optional<AccountEntity> findByCustomerId(Integer customerId);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
