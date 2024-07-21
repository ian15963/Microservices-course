package com.microservices.account.audit;

import com.microservices.account.constant.AccountConstant;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(AccountConstant.ACCOUNTS);
    }

}
