package com.example.demowithmany.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demowithmany.Dao.CartItemRepository;
import com.example.demowithmany.Dao.OrderItemRepository;
import com.example.demowithmany.Dao.OrderRepository;
import com.example.demowithmany.Dao.UserRepository;
import com.example.demowithmany.model.CartItem;
import com.example.demowithmany.model.Order;
import com.example.demowithmany.model.OrderItem;
import com.example.demowithmany.model.Product;
import com.example.demowithmany.model.User;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private ProductService productService;

    @Transactional
    public Order placeOrder(String username) {
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));

        List<CartItem> cartItems = cartItemRepo.findByUser(user);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty for user: " + username);
        }

        // Create a new Order
        Order order = new Order();
        order.setUser(user);
        order.setOrderTime(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem ci : cartItems) {
            Product product = ci.getProduct();
            int qty = ci.getQuantity();

            // Check stock
            if (product.getStock() < qty) {
                throw new RuntimeException("Not enough stock for product " + product.getId());
            }

            // Deduct stock using ProductService
            productService.reduceStock(product.getId(), qty);

            // Create OrderItem
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(product);
            oi.setQuantity(qty);
            oi.setPrice(product.getPrice().multiply(BigDecimal.valueOf(qty)));

            orderItems.add(oi);

            total = total.add(oi.getPrice());
        }

        order.setTotal(total);
        order.setItems(orderItems);

        Order savedOrder = orderRepo.save(order);

        // Save order items (cascaded if cascade ALL, else explicit)
        for (OrderItem oi : orderItems) {
            orderItemRepo.save(oi);
        }

        // Clear cart
        cartItemRepo.deleteAll(cartItems);

        return savedOrder;
    }

    public List<Order> getOrdersForUser(String username) {
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));
        return orderRepo.findByUser(user);
    }

    public Optional<Order> getOrderById(Long orderId, String username) {
        Order order = orderRepo.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
        if (!order.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Access denied to order: " + orderId);
        }
        return Optional.of(order);
    }
}

