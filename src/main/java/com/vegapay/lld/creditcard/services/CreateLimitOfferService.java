package com.vegapay.lld.creditcard.services;

import com.vegapay.lld.creditcard.dtos.CreateLimitOfferDTO;
import com.vegapay.lld.creditcard.models.LimitOffer;
import org.springframework.stereotype.Service;

@Service
public interface CreateLimitOfferService {
    LimitOffer createLimitOffer(CreateLimitOfferDTO createLimitOfferDTO);
}
