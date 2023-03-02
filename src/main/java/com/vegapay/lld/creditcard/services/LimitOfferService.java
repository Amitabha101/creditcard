package com.vegapay.lld.creditcard.services;

import com.vegapay.lld.creditcard.dtos.ActiveLimitOfferDTO;
import com.vegapay.lld.creditcard.dtos.CreateLimitOfferDTO;
import com.vegapay.lld.creditcard.dtos.UpdateLimitOfferDTO;
import com.vegapay.lld.creditcard.models.LimitOffer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LimitOfferService {
    LimitOffer createLimitOffer(CreateLimitOfferDTO createLimitOfferDTO);

    List<LimitOffer> listActiveLimitOffers(ActiveLimitOfferDTO activeLimitOfferDTO);

    void updateLimitOfferStatus(UpdateLimitOfferDTO updateLimitOfferDTO);

}
