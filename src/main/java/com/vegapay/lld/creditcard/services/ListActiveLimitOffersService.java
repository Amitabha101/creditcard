package com.vegapay.lld.creditcard.services;

import com.vegapay.lld.creditcard.dtos.ActiveLimitOfferDTO;
import com.vegapay.lld.creditcard.models.LimitOffer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ListActiveLimitOffersService {
    List<LimitOffer> listActiveLimitOffers(ActiveLimitOfferDTO activeLimitOfferDTO);
}
