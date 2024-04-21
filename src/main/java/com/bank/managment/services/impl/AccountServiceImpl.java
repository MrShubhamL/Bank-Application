package com.bank.managment.services.impl;

import com.bank.managment.entities.Accounts;
import com.bank.managment.entities.Customers;
import com.bank.managment.entities.Transactions;
import com.bank.managment.repositories.AccountRepository;
import com.bank.managment.repositories.CustomerRepository;
import com.bank.managment.repositories.TransactionRepository;
import com.bank.managment.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Accounts createAccount(Accounts accounts) {
        accounts.setEnabled(false);
        accounts.setAccountRegistrationDate(LocalDate.now().toString());
        String accountNumber = "07419200000";
        Long customerId = accounts.getCustomers().getCustomerId();
        Customers customersByCustomerId = customerRepository.getCustomersByCustomerId(customerId);
        if(customersByCustomerId!=null){
            String customerContact = customersByCustomerId.getCustomerContact();
            String substring = customerContact.substring(4, 9);
            String generatedAccountNumber = accountNumber+substring;
            accounts.setAccountNumber(generatedAccountNumber);
            if(customersByCustomerId.isEnabled()){
                return accountRepository.save(accounts);
            }
        }
        return null;
    }

    @Override
    public Transactions createTransaction(Transactions transactions) {
        transactions.setTransactionDate(LocalDate.now().toString());
        transactions.setTransactionTime(LocalTime.now().toString());
        Long accountId = transactions.getAccounts().getAccountId();
        Accounts accountsByAccountId = accountRepository.getAccountsByAccountId(accountId);
        Float transactionAmount = transactions.getTransactionAmount();
        if(accountsByAccountId.isEnabled()){
            if(transactions.getTransactionType().equals("CREDITED")){
                Float totalAmount = accountsByAccountId.getAccountBalance()+transactionAmount;
                accountsByAccountId.setAccountBalance(totalAmount);
                accountRepository.save(accountsByAccountId); //account balance will update
                return transactionRepository.save(transactions); // new transaction will be added
            } else if (transactions.getTransactionType().equals("DEBITED")) {
                Float totalAmount = accountsByAccountId.getAccountBalance()-transactionAmount;
                accountsByAccountId.setAccountBalance(totalAmount);
                accountRepository.save(accountsByAccountId);
                return transactionRepository.save(transactions);
            }
        }
        return null;
    }

    @Override
    public Accounts setEnableAccount(Long id) {
        Accounts accountsByAccountId = accountRepository.getAccountsByAccountId(id);
        accountsByAccountId.setEnabled(true);
        return accountRepository.save(accountsByAccountId);
    }
}
