package com.vegapay.lld.creditcard.services;

import com.vegapay.lld.creditcard.models.Account;
import org.springframework.stereotype.Service;

@Service
public interface GetAccountService {
    Account getAccount(Long accountId);
}
