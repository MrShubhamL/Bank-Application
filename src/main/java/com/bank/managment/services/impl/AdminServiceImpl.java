package com.bank.managment.services.impl;

import com.bank.managment.entities.Customers;
import com.bank.managment.repositories.CustomerRepository;
import com.bank.managment.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customers> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customers> getAllEnabledCustomers(boolean enable) {
        return null;
    }

    @Override
    public List<Customers> getAllDisabledCustomers(boolean disabled) {
        return null;
    }
}
