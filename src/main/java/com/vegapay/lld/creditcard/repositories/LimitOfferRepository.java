package com.vegapay.lld.creditcard.repositories;

import com.vegapay.lld.creditcard.models.LimitOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitOfferRepository extends JpaRepository<LimitOffer, Long> {

    List<LimitOffer> findAllByAccountId(Long accountId);

    LimitOffer findByLimitOfferId(Long limitOfferId);

}
