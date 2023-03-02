package com.vegapay.lld.creditcard.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.vegapay.lld.creditcard.models.LimitType;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class CreateLimitOfferDTO {
    @NonNull
    @Positive
    Long accountId;
    @NonNull
    LimitType limitType;
    @NonNull
    @Positive
    double newLimit;
    @NonNull
    LocalDateTime offerActivationTime;
    @NonNull
    LocalDateTime offerExpiryTime;
}
