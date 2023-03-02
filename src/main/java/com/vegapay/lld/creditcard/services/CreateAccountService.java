package com.vegapay.lld.creditcard.services;

import com.vegapay.lld.creditcard.dtos.CreateAccountDTO;
import com.vegapay.lld.creditcard.models.Account;
import org.springframework.stereotype.Service;

@Service
public interface CreateAccountService {
    Account createAccount(CreateAccountDTO createAccountDTO);
}
