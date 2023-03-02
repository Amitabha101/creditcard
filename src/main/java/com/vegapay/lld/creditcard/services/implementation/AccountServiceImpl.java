package com.vegapay.lld.creditcard.services.implementation;

import com.vegapay.lld.creditcard.dtos.CreateAccountDTO;
import com.vegapay.lld.creditcard.models.Account;
import com.vegapay.lld.creditcard.services.AccountService;
import com.vegapay.lld.creditcard.services.CreateAccountService;
import com.vegapay.lld.creditcard.services.GetAccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final CreateAccountService createAccountService;
    private final GetAccountService getAccountService;

    public AccountServiceImpl(CreateAccountService createAccountService, GetAccountService getAccountService) {
        this.createAccountService = createAccountService;
        this.getAccountService = getAccountService;
    }

    @Override
    public Account createAccount(CreateAccountDTO createAccountDTO) {
        return createAccountService.createAccount(createAccountDTO);
    }

    @Override
    public Account getAccount(Long accountId) {
        return getAccountService.getAccount(accountId);
    }
}
