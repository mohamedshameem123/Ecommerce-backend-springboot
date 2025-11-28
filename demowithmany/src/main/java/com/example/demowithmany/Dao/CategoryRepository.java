package com.example.demowithmany.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demowithmany.model.*;

public interface CategoryRepository extends JpaRepository<Category, Long> { }

