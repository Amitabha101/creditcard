package com.vegapay.lld.creditcard.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private Long customerId;
    private Double accountLimit;
    private Double perTransactionLimit;
    private Double lastAccountLimit;
    private Double lastPerTransactionLimit;
    private LocalDateTime accountLimitUpdateTime;
    private LocalDateTime perTransactionLimitUpdateTime;
}
