package com.bank.managment.services;

import com.bank.managment.entities.Customers;

import java.util.List;

public interface CustomerService {
    Customers createCustomer(Customers customers);
    boolean getCustomerByEmail(String email);
    boolean getCustomerByContact(String contact);
    Customers getCustomerByEmailAndPassword(String email, String password);
    Customers setCustomerEnabled(Long id);
    Customers updateCustomerDetails(Customers customers);
    boolean deleteCustomerById(Long id);
    List<Customers> getAllEnabledCustomers();
    List<Customers> getAllDisabledCustomers();
    List<Customers> findCustomerByName(String name);
}
