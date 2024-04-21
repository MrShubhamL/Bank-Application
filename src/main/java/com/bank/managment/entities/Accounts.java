package com.bank.managment.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String accountTpe;
    private String accountNumber;
    private Float accountBalance;
    private String accountRegistrationDate;
    private boolean enabled;
    @JsonBackReference
    @OneToOne
    private Customers customers;
    @JsonManagedReference
    @OneToOne
    private BankDetails bankDetails;
    @JsonManagedReference
    @OneToMany(mappedBy = "accounts", cascade = CascadeType.REMOVE)
    private List<Transactions> transactions;
}
