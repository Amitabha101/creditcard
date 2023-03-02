package com.vegapay.lld.creditcard.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "limit_offers")
public class LimitOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long limitOfferId;
    private Long accountId;
    private LimitType limitType;
    private Double newLimit;
    private LocalDateTime offerActivationTime;
    private LocalDateTime offerExpiryTime;
    private LimitOfferStatus status;
}
