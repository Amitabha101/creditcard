package com.vegapay.lld.creditcard.dtos;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveLimitOfferDTO {
    @NonNull
    @Positive
    Long accountId;
    LocalDate activeDate = LocalDate.now();
}
