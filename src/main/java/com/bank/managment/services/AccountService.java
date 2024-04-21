package com.bank.managment.services;

import com.bank.managment.entities.Accounts;
import com.bank.managment.entities.Transactions;

public interface AccountService {
    Accounts createAccount(Accounts accounts);
    Transactions createTransaction(Transactions transactions);
    Accounts setEnableAccount(Long id);
}
