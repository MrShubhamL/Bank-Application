package com.bank.managment.repositories;

import com.bank.managment.entities.Customers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
    boolean existsByCustomerContact(String contact);
    boolean existsByCustomerEmail(String email);
    Customers getCustomersByCustomerId(Long id);
    Customers getCustomersByCustomerEmailAndCustomerPassword(String email ,String password);
    @Transactional
    Integer deleteCustomersByCustomerId(Long id);
    List<Customers> getCustomersByEnabled(boolean enable);
    List<Customers> getCustomersByCustomerNameLike(String name);
}
