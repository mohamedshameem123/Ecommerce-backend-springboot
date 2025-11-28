package com.example.demowithmany.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demowithmany.service.CartItemService;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartItemService cartService;

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, Principal principal) {
        cartService.addToCart(principal.getName(), productId);
        return "redirect:/cart/view";
    }

    @GetMapping("/view")
    public String viewCart(Model model, Principal principal) {
        model.addAttribute("items", cartService.getCartItems(principal.getName()));
        return "cart";
    }
}
