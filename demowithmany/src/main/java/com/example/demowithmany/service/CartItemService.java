package com.example.demowithmany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demowithmany.Dao.CartItemRepository;
import com.example.demowithmany.Dao.ProductRepository;
import com.example.demowithmany.Dao.UserRepository;
import com.example.demowithmany.model.CartItem;
import com.example.demowithmany.model.Product;
import com.example.demowithmany.model.User;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductRepository productRepo;

    public void addToCart(String username, Long productId) {
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));

        Product product = productRepo.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        // Check if cart item already exists for this user + product
        Optional<CartItem> optional = cartItemRepo.findAll().stream()
            .filter(ci -> ci.getUser().getId().equals(user.getId())
                       && ci.getProduct().getId().equals(productId))
            .findFirst();

        CartItem cartItem;
        if (optional.isPresent()) {
            cartItem = optional.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
        }
        cartItemRepo.save(cartItem);
    }

    public List<CartItem> getCartItems(String username) {
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));
        return cartItemRepo.findByUser(user);
    }

    public void removeFromCart(String username, Long cartItemId) {
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));

        CartItem item = cartItemRepo.findById(cartItemId)
            .orElseThrow(() -> new RuntimeException("Cart item not found: " + cartItemId));

        if (!item.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to remove this cart item");
        }
        cartItemRepo.delete(item);
    }

    public void updateCartItemQuantity(String username, Long cartItemId, int newQty) {
        if (newQty < 1) {
            throw new RuntimeException("Quantity must be at least 1");
        }
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));

        CartItem item = cartItemRepo.findById(cartItemId)
            .orElseThrow(() -> new RuntimeException("Cart item not found: " + cartItemId));

        if (!item.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        item.setQuantity(newQty);
        cartItemRepo.save(item);
    }

    public void clearCart(String username) {
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));
        List<CartItem> items = cartItemRepo.findByUser(user);
        cartItemRepo.deleteAll(items);
    }
}
