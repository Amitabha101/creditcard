package com.vegapay.lld.creditcard.controllers;

import com.vegapay.lld.creditcard.dtos.CreateAccountDTO;
import com.vegapay.lld.creditcard.models.Account;
import com.vegapay.lld.creditcard.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody @Valid CreateAccountDTO createRequest) {
        Account account = accountService.createAccount(createRequest);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") Long accountId) {
        Account account = accountService.getAccount(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
