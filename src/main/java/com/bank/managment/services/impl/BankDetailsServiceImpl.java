package com.bank.managment.services.impl;

import com.bank.managment.entities.BankDetails;
import com.bank.managment.entities.BankLocations;
import com.bank.managment.repositories.AccountRepository;
import com.bank.managment.repositories.BankDetailsRepository;
import com.bank.managment.repositories.BankLocationRepository;
import com.bank.managment.services.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankDetailsServiceImpl implements BankDetailsService {
    @Autowired
    BankDetailsRepository bankDetailsRepository;
    @Autowired
    BankLocationRepository bankLocationRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public BankDetails createBankDetails(BankDetails bankDetails) {
        return bankDetailsRepository.save(bankDetails);
    }
    @Override
    public BankLocations createBankLocation(BankLocations bankLocations) {
        return bankLocationRepository.save(bankLocations);
    }

}
