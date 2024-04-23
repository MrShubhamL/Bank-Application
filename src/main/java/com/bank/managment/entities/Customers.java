package com.bank.managment.entities;

import com.bank.managment.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String customerName;
    private String customerContact;
    private String customerEmail;
    private String customerPassword;
    private String customerRegistrationDate;
    private UserRole customerRole;
    private boolean enabled;
    @JsonManagedReference
    @OneToOne(mappedBy = "customers", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Accounts accounts;
}
