package com.bank.managment.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;
    private String bankName;
    private String bankIfsc;
    @JsonBackReference
    @Transient
    private Accounts accounts;

    @JsonManagedReference
    @OneToOne(mappedBy = "bankDetails")
    private BankLocations bankLocations;
}
