package com.bank.managment.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private Float transactionAmount;
    private String transactionType;
    private String transactionDate;
    private String transactionTime;
    @JsonBackReference
    @ManyToOne
    private Accounts accounts;

}
