package com.vegapay.lld.creditcard.services.implementation;

import com.vegapay.lld.creditcard.dtos.CreateAccountDTO;
import com.vegapay.lld.creditcard.exeption.InvalidArgumentException;
import com.vegapay.lld.creditcard.models.Account;
import com.vegapay.lld.creditcard.repositories.AccountRepository;
import com.vegapay.lld.creditcard.services.CreateAccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateAccountServiceImpl implements CreateAccountService {
    private final AccountRepository accountRepository;

    public CreateAccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(CreateAccountDTO createAccountDTO) {
        validateAccountLimits(createAccountDTO.getAccountLimit(), createAccountDTO.getPerTransactionLimit());
        Account account = Account.builder()
                .customerId(createAccountDTO.getCustomerId())
                .accountLimit(createAccountDTO.getAccountLimit())
                .perTransactionLimit(createAccountDTO.getPerTransactionLimit())
                .lastAccountLimit(createAccountDTO.getAccountLimit())
                .lastPerTransactionLimit(0.0)
                .accountLimitUpdateTime(LocalDateTime.now())
                .perTransactionLimitUpdateTime(LocalDateTime.now())
                .build();
        return accountRepository.save(account);
    }

    private void validateAccountLimits(double accountLimit, double perTransactionLimit) {
        if (accountLimit < perTransactionLimit) {
            throw new InvalidArgumentException("AccountLimit can not be smaller than PerTransactionLimit");
        }
    }
}
