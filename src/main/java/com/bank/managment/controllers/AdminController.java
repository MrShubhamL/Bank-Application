package com.bank.managment.controllers;

import com.bank.managment.entities.Accounts;
import com.bank.managment.entities.BankDetails;
import com.bank.managment.entities.BankLocations;
import com.bank.managment.entities.Customers;
import com.bank.managment.services.AccountService;
import com.bank.managment.services.BankDetailsService;
import com.bank.managment.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin/service")
public class AdminController {
    @Autowired
    BankDetailsService bankDetailsService;
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;
    @PostMapping("/addBank")
    public ResponseEntity<?> createBankDetails(@RequestBody BankDetails bankDetails){
        BankDetails bd = bankDetailsService.createBankDetails(bankDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(bd);
    }
    @PostMapping("/addBankLocation")
    public ResponseEntity<?> createBankLocation(@RequestBody BankLocations bankLocations){
        BankLocations bl = bankDetailsService.createBankLocation(bankLocations);
        return ResponseEntity.status(HttpStatus.CREATED).body(bl);
    }

    @PostMapping("/enableCustomer/{id}")
    public ResponseEntity<?> setEnableCustomer(@PathVariable Long id){
        Customers customers = customerService.setCustomerEnabled(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customers);
    }

    @PostMapping("/enableAccount/{id}")
    public ResponseEntity<?> setEnableAccount(@PathVariable Long id){
        Accounts accounts = accountService.setEnableAccount(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accounts);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        boolean b = customerService.deleteCustomerById(id);
        if(b){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer Deleted Successfully..");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to remove customer details");
    }

    @GetMapping("/getAllEnabledCustomers")
    public ResponseEntity<Object> getAllEnabled(){
        List<Customers> allEnabledCustomers = customerService.getAllEnabledCustomers();
        if(allEnabledCustomers.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No active customers found!");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(allEnabledCustomers);
    }
    @GetMapping("/getAllDisabledCustomers")
    public ResponseEntity<Object> getAllDisabled(){
        List<Customers> allDisabledCustomers = customerService.getAllDisabledCustomers();
        if(allDisabledCustomers.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No disabled customers found!");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(allDisabledCustomers);
    }

    @GetMapping("/searchName/{name}")
    public ResponseEntity<Object> searchCustomerByName(@PathVariable String name){
        List<Customers> customerByName = customerService.findCustomerByName(name);
        if(customerByName.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not existed!");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerByName);
    }

}
