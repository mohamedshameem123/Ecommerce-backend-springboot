package com.example.demowithmany.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")  
public class Order {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private LocalDateTime orderTime;

	    private BigDecimal total;

	    @ManyToOne
	    private User user;

	    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	    private List<OrderItem> items;

		public Order() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Order(Long id, LocalDateTime orderTime, BigDecimal total, User user, List<OrderItem> items) {
			super();
			this.id = id;
			this.orderTime = orderTime;
			this.total = total;
			this.user = user;
			this.items = items;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public LocalDateTime getOrderTime() {
			return orderTime;
		}

		public void setOrderTime(LocalDateTime orderTime) {
			this.orderTime = orderTime;
		}

		public BigDecimal getTotal() {
			return total;
		}

		public void setTotal(BigDecimal total) {
			this.total = total;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public List<OrderItem> getItems() {
			return items;
		}

		public void setItems(List<OrderItem> items) {
			this.items = items;
		}


}
