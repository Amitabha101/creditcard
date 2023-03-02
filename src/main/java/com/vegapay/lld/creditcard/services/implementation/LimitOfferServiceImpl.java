package com.vegapay.lld.creditcard.services.implementation;

import com.vegapay.lld.creditcard.dtos.ActiveLimitOfferDTO;
import com.vegapay.lld.creditcard.dtos.CreateLimitOfferDTO;
import com.vegapay.lld.creditcard.dtos.UpdateLimitOfferDTO;
import com.vegapay.lld.creditcard.models.LimitOffer;
import com.vegapay.lld.creditcard.services.CreateLimitOfferService;
import com.vegapay.lld.creditcard.services.LimitOfferService;
import com.vegapay.lld.creditcard.services.ListActiveLimitOffersService;
import com.vegapay.lld.creditcard.services.UpdateLimitOfferStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimitOfferServiceImpl implements LimitOfferService {
    private final CreateLimitOfferService createLimitOfferService;
    private final ListActiveLimitOffersService listActiveLimitOffersService;
    private final UpdateLimitOfferStatusService updateLimitOfferStatusService;

    public LimitOfferServiceImpl(CreateLimitOfferService createLimitOfferService, ListActiveLimitOffersService listActiveLimitOffersService, UpdateLimitOfferStatusService updateLimitOfferStatusService) {
        this.createLimitOfferService = createLimitOfferService;
        this.listActiveLimitOffersService = listActiveLimitOffersService;
        this.updateLimitOfferStatusService = updateLimitOfferStatusService;
    }

    @Override
    public LimitOffer createLimitOffer(CreateLimitOfferDTO createLimitOfferDTO) {
        return createLimitOfferService.createLimitOffer(createLimitOfferDTO);
    }

    @Override
    public List<LimitOffer> listActiveLimitOffers(ActiveLimitOfferDTO activeLimitOfferDTO) {
        return listActiveLimitOffersService.listActiveLimitOffers(activeLimitOfferDTO);
    }

    @Override
    public void updateLimitOfferStatus(UpdateLimitOfferDTO updateLimitOfferDTO) {
        updateLimitOfferStatusService.updateLimitOfferStatus(updateLimitOfferDTO);
    }
}
