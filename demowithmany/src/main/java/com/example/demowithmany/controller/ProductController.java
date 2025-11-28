package com.example.demowithmany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demowithmany.service.ProductService;



@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }
}
