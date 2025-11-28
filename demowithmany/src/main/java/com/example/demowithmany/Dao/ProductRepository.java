package com.example.demowithmany.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demowithmany.model.*;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}

