package com.vegapay.lld.creditcard.repositories;

import com.vegapay.lld.creditcard.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountId(Long accountId);

}
