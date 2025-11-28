package com.example.demowithmany.Dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demowithmany.model.*;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
}
