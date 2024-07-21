package com.microservices.loans.audit;

import com.microservices.loans.constants.LoansConstants;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(LoansConstants.HOME_LOAN);
    }
}
