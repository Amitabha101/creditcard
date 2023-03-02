package com.vegapay.lld.creditcard.controllers;

import com.vegapay.lld.creditcard.dtos.ActiveLimitOfferDTO;
import com.vegapay.lld.creditcard.dtos.CreateLimitOfferDTO;
import com.vegapay.lld.creditcard.dtos.UpdateLimitOfferDTO;
import com.vegapay.lld.creditcard.models.LimitOffer;
import com.vegapay.lld.creditcard.services.LimitOfferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/limitOffer")
public class LimitOfferController {

    private final LimitOfferService limitOfferService;

    public LimitOfferController(LimitOfferService limitOfferService) {
        this.limitOfferService = limitOfferService;
    }

    @PostMapping
    public ResponseEntity<LimitOffer> createLimitOffer(@RequestBody @Valid CreateLimitOfferDTO createRequest) {
        LimitOffer limitOffer = limitOfferService.createLimitOffer(createRequest);
        return new ResponseEntity<>(limitOffer, HttpStatus.CREATED);
    }

    @GetMapping
    public List<LimitOffer> getLimitOffers(@RequestBody ActiveLimitOfferDTO getRequest) {
        return limitOfferService.listActiveLimitOffers(getRequest);
    }

    @PutMapping
    public void updateLimitOffer(@RequestBody UpdateLimitOfferDTO updateRequest) {
        limitOfferService.updateLimitOfferStatus(updateRequest);
    }
}
