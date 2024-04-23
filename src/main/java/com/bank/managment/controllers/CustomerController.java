package com.bank.managment.controllers;

import com.bank.managment.configurations.Authorization;
import com.bank.managment.entities.Accounts;
import com.bank.managment.entities.Customers;
import com.bank.managment.entities.Transactions;
import com.bank.managment.handler.DuplicateRecordFoundException;
import com.bank.managment.services.AccountService;
import com.bank.managment.services.CustomerService;
import com.bank.managment.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/service")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;
    @Autowired
    EmailService emailService;
    @Autowired
    Authorization authorization;

    @PostMapping("/register")
    public ResponseEntity<Object> createCustomer(@RequestBody Customers customers) throws MessagingException {
        boolean customerByEmail = customerService.getCustomerByEmail(customers.getCustomerEmail());
        boolean customerByContact = customerService.getCustomerByContact(customers.getCustomerContact());
        if (customerByEmail) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already existed!");
        } else if (customerByContact) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contact already existed!");
        } else {
            Customers customer = customerService.createCustomer(customers);
            emailService.sendCustomerRegistrationEmail(customers);
            return ResponseEntity.status(HttpStatus.CREATED).body(customer);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> getCustomerByCredentials(@RequestBody Customers customers) {
        String customerEmail = customers.getCustomerEmail();
        String customerPassword = customers.getCustomerPassword();
        Customers customerByEmailAndPassword = customerService.getCustomerByEmailAndPassword(customerEmail, customerPassword);
        if (customerByEmailAndPassword != null) {
            if (customerByEmailAndPassword.isEnabled()) {
                authorization.setEmail(customerByEmailAndPassword.getCustomerEmail());
                authorization.setPassword(customerByEmailAndPassword.getCustomerPassword());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerByEmailAndPassword);
            } else {
                authorization.logout();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer is not approved!!");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Credentials!!");
    }

    @PostMapping("/addAccount")
    public ResponseEntity<?> createBankAccount(@RequestBody Accounts accounts) {
        boolean authenticate = authorization.authenticate();
        if (authenticate) {
            Accounts account = accountService.createAccount(accounts);
            if (account != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(account);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer is not approved!!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please login with your credentials!!");
        }
    }

    @PostMapping("/addTransaction")
    public ResponseEntity<?> createTransaction(@RequestBody Transactions transactions) {
//        System.out.println(transactions.toString());
        Transactions transaction = accountService.createTransaction(transactions);
        if (transaction != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account is not activated");
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomerDetails(@RequestBody Customers customers) {
        Customers customers1 = customerService.updateCustomerDetails(customers);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customers1);
    }

    @GetMapping("/allTransactions/{id}")
    public ResponseEntity<?> getAllTransactions(@PathVariable Long id){
        boolean authenticate = authorization.authenticate();
        if(authenticate){
            List<Transactions> allTransactions = accountService.getAllTransactionsByAccountId(id);
            if(allTransactions.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Transaction Found!!");
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(allTransactions);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Customer is not authorized!!");
    }

}
