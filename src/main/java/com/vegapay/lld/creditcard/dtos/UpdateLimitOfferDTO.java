package com.vegapay.lld.creditcard.dtos;

import com.vegapay.lld.creditcard.models.LimitOfferStatus;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NonNull;

@Data
public class UpdateLimitOfferDTO {
    @NonNull
    @Positive
    Long limitOfferId;
    @NonNull
    LimitOfferStatus status;
}
