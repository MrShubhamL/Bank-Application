package com.bank.managment.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;
    private String bankName;
    private String bankIfsc;
    @JsonBackReference
    @OneToOne(mappedBy = "bankDetails")
    private Accounts accounts;

    @JsonManagedReference
    @OneToOne(mappedBy = "bankDetails")
    private BankLocations bankLocations;
}
