package com.vegapay.lld.creditcard.services.implementation;

import com.vegapay.lld.creditcard.exeption.NotFoundException;
import com.vegapay.lld.creditcard.models.Account;
import com.vegapay.lld.creditcard.repositories.AccountRepository;
import com.vegapay.lld.creditcard.services.GetAccountService;
import org.springframework.stereotype.Service;

@Service
public class GetAccountServiceImpl implements GetAccountService {
    private final AccountRepository accountRepository;

    public GetAccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount(Long accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        if (account == null) {
            throw new NotFoundException("Account not found for account id: " + accountId);
        }
        return account;
    }
}
