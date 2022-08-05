package com.example.northwindserver.repositories;

import com.example.northwindserver.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}