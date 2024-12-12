package com.revshop.p1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Favorite {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "buyer_id", nullable = false)
	    private Buyer buyer;

	    @ManyToOne
	    @JoinColumn(name = "product_id", nullable = false)
	    private Product product;

	    
	    public Favorite() {}

	    public Favorite(Buyer buyer, Product product) {
	        this.buyer = buyer;
	        this.product = product;
	    }

	    public Long getId() {
	        return id;
	    }

	    public Buyer getBuyer() {
	        return buyer;
	    }

	    public void setBuyer(Buyer buyer) {
	        this.buyer = buyer;
	    }

	    public Product getProduct() {
	        return product;
	    }

	    public void setProduct(Product product) {
	        this.product = product;
	    }
	}

