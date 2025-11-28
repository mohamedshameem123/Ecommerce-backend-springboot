package com.example.demowithmany.model;

import jakarta.persistence.*;

@Entity
public class Role {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    public Role() {
		super();
	}
		private String name;
		public Role(Long id, String name) {
			super();
			this.id = id;
			this.name = name;
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
}
