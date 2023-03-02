package com.vegapay.lld.creditcard.services.implementation;

import com.vegapay.lld.creditcard.dtos.CreateLimitOfferDTO;
import com.vegapay.lld.creditcard.exeption.InvalidArgumentException;
import com.vegapay.lld.creditcard.exeption.NotFoundException;
import com.vegapay.lld.creditcard.models.Account;
import com.vegapay.lld.creditcard.models.LimitOffer;
import com.vegapay.lld.creditcard.models.LimitOfferStatus;
import com.vegapay.lld.creditcard.models.LimitType;
import com.vegapay.lld.creditcard.repositories.AccountRepository;
import com.vegapay.lld.creditcard.repositories.LimitOfferRepository;
import com.vegapay.lld.creditcard.services.CreateLimitOfferService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.EnumSet;

@Service
public class CreateLimitOfferServiceImpl implements CreateLimitOfferService {
    private final AccountRepository accountRepository;
    private final LimitOfferRepository limitOfferRepository;

    public CreateLimitOfferServiceImpl(AccountRepository accountRepository, LimitOfferRepository limitOfferRepository) {
        this.accountRepository = accountRepository;
        this.limitOfferRepository = limitOfferRepository;
    }

    public LimitOffer createLimitOffer(CreateLimitOfferDTO createLimitOfferDTO) {
        Account account = getAccount(createLimitOfferDTO.getAccountId());
        validateCreateLimitOfferDTO(createLimitOfferDTO, account);
        LimitOffer limitOffer = createLimitOffer(account, createLimitOfferDTO);
        saveLimitOffer(limitOffer);
        return limitOffer;
    }

    private Account getAccount(Long accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        if (account == null) {
            throw new NotFoundException("Account not found for account id: " + accountId);
        }
        return account;
    }

    private void validateCreateLimitOfferDTO(CreateLimitOfferDTO createLimitOfferDTO, Account account) {
        validateLimitType(createLimitOfferDTO.getLimitType());
        validateNewLimit(createLimitOfferDTO.getLimitType(), createLimitOfferDTO.getNewLimit(), account);
        validateOfferTime(createLimitOfferDTO.getOfferActivationTime(), createLimitOfferDTO.getOfferExpiryTime());
    }

    private void validateLimitType(LimitType limitType) {
        if (!EnumSet.of(LimitType.ACCOUNT_LIMIT, LimitType.PER_TRANSACTION_LIMIT).contains(limitType)) {
            throw new InvalidArgumentException("Invalid Limit type provided");
        }
    }

    private void validateNewLimit(LimitType limitType, Double newLimit, Account account) {
        if (limitType == LimitType.ACCOUNT_LIMIT && newLimit <= account.getAccountLimit()) {
            throw new InvalidArgumentException("New account limit should be greater than current account limit");
        }
        if (limitType == LimitType.PER_TRANSACTION_LIMIT && newLimit <= account.getPerTransactionLimit()) {
            throw new InvalidArgumentException("New PerTransaction limit should be greater than current PerTransaction limit");
        }
    }

    private void validateOfferTime(LocalDateTime offerActivationTime, LocalDateTime offerExpiryTime) {
        if (offerActivationTime.isAfter(offerExpiryTime)) {
            throw new InvalidArgumentException("Offer expiry time should be after offer activation time");
        }
    }

    private LimitOffer createLimitOffer(Account account, CreateLimitOfferDTO createLimitOfferDTO) {
        return LimitOffer.builder()
                .accountId(createLimitOfferDTO.getAccountId())
                .limitType(createLimitOfferDTO.getLimitType())
                .newLimit(createLimitOfferDTO.getNewLimit())
                .offerActivationTime(createLimitOfferDTO.getOfferActivationTime())
                .offerExpiryTime(createLimitOfferDTO.getOfferExpiryTime())
                .status(LimitOfferStatus.PENDING)
                .build();
    }

    private void saveLimitOffer(LimitOffer limitOffer) {
        limitOfferRepository.save(limitOffer);
    }

}
