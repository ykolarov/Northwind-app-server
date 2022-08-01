package com.example.northwindserver.repositories;

import com.example.northwindserver.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findById(int id);



}