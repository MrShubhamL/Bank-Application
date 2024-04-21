package com.bank.managment.repositories;

import com.bank.managment.entities.BankLocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankLocationRepository extends JpaRepository<BankLocations, Long> {
}
