package com.agilemonkeys.crmapi.repository;

import com.agilemonkeys.crmapi.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Serializable> {
    Optional<Customer> findById(int id);
    Page<Customer> findAll(Pageable pageable);
    boolean existsById(int id);
}
