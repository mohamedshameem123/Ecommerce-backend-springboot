package com.example.demowithmany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demowithmany.Dao.CategoryRepository;
import com.example.demowithmany.Dao.ProductRepository;
import com.example.demowithmany.model.Category;
import com.example.demowithmany.model.Product;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    public List<Product> getProductsByCategoryId(Long categoryId) {
        Category cat = categoryRepo.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found with id " + categoryId));
        return productRepo.findByCategory(cat);
    }

    public Product saveProduct(Product product) {
        // If category is set by id only, fetch the category entity
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category cat = categoryRepo.findById(product.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(cat);
        }
        return productRepo.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepo.deleteById(productId);
    }

    public void reduceStock(Long productId, int amount) {
        Product p = productRepo.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        int newStock = p.getStock() - amount;
        if (newStock < 0) {
            throw new RuntimeException("Insufficient stock for product: " + productId);
        }
        p.setStock(newStock);
        productRepo.save(p);
    }

}

