package com.example.demowithmany.Dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demowithmany.model.*;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
