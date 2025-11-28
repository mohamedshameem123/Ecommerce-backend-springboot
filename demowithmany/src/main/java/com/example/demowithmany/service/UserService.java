package com.example.demowithmany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demowithmany.Dao.RoleRepository;
import com.example.demowithmany.Dao.UserRepository;
import com.example.demowithmany.model.Role;
import com.example.demowithmany.model.User;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    public User registerUser(String username, String rawPassword) {
        if (userRepo.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setEnabled(true);
        Role role = roleRepo.findByName("ROLE_USER")
                         .orElseGet(() -> roleRepo.save(new Role(null, "ROLE_USER")));
        user.getRoles().add(role);
        return userRepo.save(user);
    }
}


