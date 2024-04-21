package com.bank.managment.services;

import com.bank.managment.entities.Customers;

import java.util.List;

public interface AdminService {
    List<Customers> getAllCustomers();
    List<Customers> getAllEnabledCustomers(boolean enable);
    List<Customers> getAllDisabledCustomers(boolean disabled);
}
