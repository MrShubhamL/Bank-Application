package com.bank.managment.services;

import com.bank.managment.entities.Customers;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendCustomerRegistrationEmail(Customers customers) throws MessagingException;
}
