package com.example.demowithmany.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demowithmany.model.*;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}

