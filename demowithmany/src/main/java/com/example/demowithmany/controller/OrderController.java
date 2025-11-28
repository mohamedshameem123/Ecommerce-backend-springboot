package com.example.demowithmany.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demowithmany.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController 
 {
    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public String placeOrder(Principal principal) {
        orderService.placeOrder(principal.getName());
        return "redirect:/order/confirmation";
    }

    @GetMapping("/confirmation")
    public String confirmationPage() {
        return "order-confirmation";
    }
}
