package com.vegapay.lld.creditcard.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateAccountDTO {
    @NotNull
    @Positive
    private Long customerId;
    @NotNull
    @Positive
    private Double accountLimit;
    @NotNull
    @Positive
    private Double perTransactionLimit;
}
