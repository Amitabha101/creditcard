package com.vegapay.lld.creditcard.services;

import com.vegapay.lld.creditcard.models.LimitType;
import org.springframework.stereotype.Service;

@Service
public interface AccountUpdateService {
    void updateAccount(long accountId, LimitType limitType, Double newLimit);
}
