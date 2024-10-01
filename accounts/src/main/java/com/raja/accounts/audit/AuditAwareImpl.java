package com.raja.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    /**
     * Returns the current auditor, which is the identifier of the user or system
     * performing the audit operation.
     *
     * @return an Optional containing the current auditor, or an empty Optional if
     * no auditor is available.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS"); // "ACCOUNTS_MS" is the fixed auditor identifier
    }
}
