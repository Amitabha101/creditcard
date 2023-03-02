package com.vegapay.lld.creditcard.services.implementation;

import com.vegapay.lld.creditcard.dtos.ActiveLimitOfferDTO;
import com.vegapay.lld.creditcard.exeption.NotFoundException;
import com.vegapay.lld.creditcard.models.Account;
import com.vegapay.lld.creditcard.models.LimitOffer;
import com.vegapay.lld.creditcard.models.LimitOfferStatus;
import com.vegapay.lld.creditcard.repositories.AccountRepository;
import com.vegapay.lld.creditcard.repositories.LimitOfferRepository;
import com.vegapay.lld.creditcard.services.ListActiveLimitOffersService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListActiveLimitOffersServiceImpl implements ListActiveLimitOffersService {
    private final AccountRepository accountRepository;
    private final LimitOfferRepository limitOfferRepository;

    public ListActiveLimitOffersServiceImpl(AccountRepository accountRepository, LimitOfferRepository limitOfferRepository) {
        this.accountRepository = accountRepository;
        this.limitOfferRepository = limitOfferRepository;
    }

    public List<LimitOffer> listActiveLimitOffers(ActiveLimitOfferDTO activeLimitOfferDTO) {
        Account account = getAccount(activeLimitOfferDTO.getAccountId());
        List<LimitOffer> limitOffers = limitOfferRepository
                .findAllByAccountId(activeLimitOfferDTO.getAccountId());
        return filterActiveLimitOffers(limitOffers, activeLimitOfferDTO.getActiveDate());
    }

    private Account getAccount(Long accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        if (account == null) {
            throw new NotFoundException("Account not found");
        }
        return account;
    }

    private List<LimitOffer> filterActiveLimitOffers(List<LimitOffer> limitOffers, LocalDate activeDate) {
        List<LimitOffer> activeLimitOffers = new ArrayList<>();
        for (LimitOffer limitOffer : limitOffers) {
            if (isLimitOfferActive(limitOffer, activeDate)) {
                activeLimitOffers.add(limitOffer);
            }
        }
        return activeLimitOffers;
    }

    private boolean isLimitOfferActive(LimitOffer limitOffer, LocalDate activeDate) {
        return limitOffer.getStatus().equals(LimitOfferStatus.PENDING)
                && limitOffer.getOfferActivationTime().isBefore(activeDate.atStartOfDay())
                && limitOffer.getOfferExpiryTime().isAfter(activeDate.atStartOfDay());
    }


}
