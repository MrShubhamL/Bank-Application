package com.bank.managment.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BankLocations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;
    private String address;
    @JsonBackReference
    @OneToOne
    private BankDetails bankDetails;
}
