package com.bank.managment.repositories;

import com.bank.managment.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> getTransactionsByAccountsAccountId(Long id);
}
