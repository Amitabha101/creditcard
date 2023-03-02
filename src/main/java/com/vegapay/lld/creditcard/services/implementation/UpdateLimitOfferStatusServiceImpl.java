package com.vegapay.lld.creditcard.services.implementation;

import com.vegapay.lld.creditcard.dtos.UpdateLimitOfferDTO;
import com.vegapay.lld.creditcard.exeption.InvalidArgumentException;
import com.vegapay.lld.creditcard.exeption.NotFoundException;
import com.vegapay.lld.creditcard.models.LimitOffer;
import com.vegapay.lld.creditcard.models.LimitOfferStatus;
import com.vegapay.lld.creditcard.repositories.LimitOfferRepository;
import com.vegapay.lld.creditcard.services.AccountUpdateService;
import com.vegapay.lld.creditcard.services.UpdateLimitOfferStatusService;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
public class UpdateLimitOfferStatusServiceImpl implements UpdateLimitOfferStatusService {
    private final LimitOfferRepository limitOfferRepository;
    private final AccountUpdateService accountUpdateService;

    public UpdateLimitOfferStatusServiceImpl(LimitOfferRepository limitOfferRepository, AccountUpdateService accountUpdateService) {
        this.limitOfferRepository = limitOfferRepository;
        this.accountUpdateService = accountUpdateService;
    }

    public void updateLimitOfferStatus(UpdateLimitOfferDTO updateLimitOfferDTO) {
        validateLimitOfferStatus(updateLimitOfferDTO.getStatus());
        LimitOffer limitOffer = getLimitOffer(updateLimitOfferDTO.getLimitOfferId());
        if (updateLimitOfferDTO.getStatus().equals(LimitOfferStatus.ACCEPTED)) {
            accountUpdateService.updateAccount(limitOffer.getAccountId(), limitOffer.getLimitType(), limitOffer.getNewLimit());
        }
        limitOffer.setStatus(updateLimitOfferDTO.getStatus());
        limitOfferRepository.save(limitOffer);
    }

    private void validateLimitOfferStatus(LimitOfferStatus limitOfferStatus) {
        if (!EnumSet.of(LimitOfferStatus.ACCEPTED, LimitOfferStatus.REJECTED).contains(limitOfferStatus)) {
            throw new InvalidArgumentException("Invalid LimitOfferStatus provided");
        }
    }

    private LimitOffer getLimitOffer(Long limitOfferId) {
        LimitOffer limitOffer = limitOfferRepository.findByLimitOfferId(limitOfferId);
        if (limitOffer == null) {
            throw new NotFoundException("Limit offer not found");
        }
        return limitOffer;
    }
}
