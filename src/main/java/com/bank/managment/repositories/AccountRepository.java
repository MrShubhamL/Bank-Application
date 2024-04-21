package com.bank.managment.repositories;

import com.bank.managment.entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {
    Accounts getAccountsByAccountId(Long id);
}
