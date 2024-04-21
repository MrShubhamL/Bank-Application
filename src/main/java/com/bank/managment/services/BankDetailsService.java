package com.bank.managment.services;

import com.bank.managment.entities.BankDetails;
import com.bank.managment.entities.BankLocations;

public interface BankDetailsService {
    BankDetails createBankDetails(BankDetails bankDetails);
    BankLocations createBankLocation(BankLocations bankLocations);

}
