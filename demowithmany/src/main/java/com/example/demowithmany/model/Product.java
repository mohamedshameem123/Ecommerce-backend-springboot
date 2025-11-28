package com.example.demowithmany.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
public class Product {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String description;
	    private BigDecimal price;
	    private int stock;

	    @ManyToOne
	    @JoinColumn(name = "category_id")
	    private Category category;

		public Product() {
			super();
			
		}

		public Product(Long id, String name, String description, BigDecimal price, int stock, Category category) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.price = price;
			this.stock = stock;
			this.category = category;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}
	    

}
