package com.example.demowithmany.model;

import jakarta.persistence.*;

@Entity
public class CartItem {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private int quantity;

	    @ManyToOne
	    private Product product;

	    @ManyToOne
	    private User user;

		public CartItem() {
			super();

		}

		public CartItem(Long id, int quantity, Product product, User user) {
			super();
			this.id = id;
			this.quantity = quantity;
			this.product = product;
			this.user = user;
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

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
	    


}
