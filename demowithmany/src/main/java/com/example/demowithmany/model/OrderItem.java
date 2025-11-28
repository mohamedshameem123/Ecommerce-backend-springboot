package com.example.demowithmany.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
public class OrderItem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private BigDecimal price;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItem(Long id, int quantity, BigDecimal price, Product product, Order order) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.product = product;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
