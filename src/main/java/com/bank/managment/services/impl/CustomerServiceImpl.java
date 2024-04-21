package com.bank.managment.services.impl;

import com.bank.managment.entities.Customers;
import com.bank.managment.enums.UserRole;
import com.bank.managment.repositories.CustomerRepository;
import com.bank.managment.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Cookie;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customers createCustomer(Customers customers) {
        customers.setEnabled(false);
        customers.setCustomerRegistrationDate(LocalDate.now().toString());
        customers.setCustomerRole(UserRole.NORMAL);
        return customerRepository.save(customers);
    }

    @Override
    public boolean getCustomerByEmail(String email) {
        return customerRepository.existsByCustomerEmail(email);
    }

    @Override
    public boolean getCustomerByContact(String contact) {
        return customerRepository.existsByCustomerContact(contact);
    }

    @Override
    public Customers getCustomerByEmailAndPassword(String email, String password) {
        return customerRepository.getCustomersByCustomerEmailAndCustomerPassword(email, password);
    }

    @Override
    public Customers setCustomerEnabled(Long id) {
        Customers customersByCustomerId = customerRepository.getCustomersByCustomerId(id);
        customersByCustomerId.setEnabled(true);
        return customerRepository.save(customersByCustomerId);
    }

    @Override
    public Customers updateCustomerDetails(Customers customers) {
        Customers customersByCustomerId = customerRepository.getCustomersByCustomerId(customers.getCustomerId());
        customers.setCustomerRegistrationDate(customersByCustomerId.getCustomerRegistrationDate());
        customers.setCustomerRole(customersByCustomerId.getCustomerRole());
        customers.setEnabled(true);
        return customerRepository.save(customers);
    }

    @Override
    public boolean deleteCustomerById(Long id) {
        Integer integer = customerRepository.deleteCustomersByCustomerId(id);
        return integer == 1;
    }

    @Override
    public List<Customers> getAllEnabledCustomers() {
        return customerRepository.getCustomersByEnabled(true);
    }

    @Override
    public List<Customers> getAllDisabledCustomers() {
        return customerRepository.getCustomersByEnabled(false);
    }

    @Override
    public List<Customers> findCustomerByName(String name) {
        return customerRepository.getCustomersByCustomerNameLike("%"+name+"%");
    }

}
