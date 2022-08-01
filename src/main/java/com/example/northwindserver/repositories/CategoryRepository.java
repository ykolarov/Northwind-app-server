package com.example.northwindserver.repositories;

import com.example.northwindserver.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}