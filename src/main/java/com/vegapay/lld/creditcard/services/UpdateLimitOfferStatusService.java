package com.vegapay.lld.creditcard.services;

import com.vegapay.lld.creditcard.dtos.UpdateLimitOfferDTO;
import org.springframework.stereotype.Service;

@Service
public interface UpdateLimitOfferStatusService {
    void updateLimitOfferStatus(UpdateLimitOfferDTO updateLimitOfferDTO);
}
