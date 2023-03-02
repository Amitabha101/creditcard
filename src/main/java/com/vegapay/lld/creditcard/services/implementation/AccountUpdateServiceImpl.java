package com.vegapay.lld.creditcard.services.implementation;

import com.vegapay.lld.creditcard.exeption.NotFoundException;
import com.vegapay.lld.creditcard.models.Account;
import com.vegapay.lld.creditcard.models.LimitType;
import com.vegapay.lld.creditcard.repositories.AccountRepository;
import com.vegapay.lld.creditcard.services.AccountUpdateService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountUpdateServiceImpl implements AccountUpdateService {
    private final AccountRepository accountRepository;

    public AccountUpdateServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void updateAccount(long accountId, LimitType limitType, Double newLimit) {
        Account account = getAccount(accountId);
        if (limitType.equals(LimitType.ACCOUNT_LIMIT)) {
            account.setLastAccountLimit(account.getAccountLimit());
            account.setAccountLimit(newLimit);
            account.setAccountLimitUpdateTime(LocalDateTime.now());
        } else if (limitType.equals(LimitType.PER_TRANSACTION_LIMIT)) {
            account.setLastPerTransactionLimit(account.getPerTransactionLimit());
            account.setPerTransactionLimit(newLimit);
            account.setPerTransactionLimitUpdateTime(LocalDateTime.now());
        }
        accountRepository.save(account);
    }

    private Account getAccount(Long accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        if (account == null) {
            throw new NotFoundException("Account not found for account id: " + accountId);
        }
        return account;
    }
}
