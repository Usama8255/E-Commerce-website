
package com.revshop.p1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItems {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "order_id", nullable = false)
	    private Orders order;

	    @ManyToOne
	    @JoinColumn(name = "product_id", nullable = false)
	    private Product product;

	    private int quantity;

	    private double unitPrice;

	    private double totalPrice;

	    // Constructors, getters, setters
	    public OrderItems() {}

	    public OrderItems(Orders order, Product product, int quantity, double unitPrice) {
	        this.order = order;
	        this.product = product;
	        this.quantity = quantity;
	        this.unitPrice = unitPrice;
	        this.totalPrice = unitPrice * quantity;
	    }

	    public Long getId() {
	        return id;
	    }

	    public Orders getOrder() {
	        return order;
	    }

	    public void setOrder(Orders order) {
	        this.order = order;
	    }

	    public Product getProduct() {
	        return product;
	    }

	    public void setProduct(Product product) {
	        this.product = product;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

	    public double getUnitPrice() {
	        return unitPrice;
	    }

	    public void setUnitPrice(double unitPrice) {
	        this.unitPrice = unitPrice;
	    }

	    public double getTotalPrice() {
	        return totalPrice;
	    }

	    public void setTotalPrice(double totalPrice) {
	        this.totalPrice = totalPrice;
	    }

		@Override
		public String toString() {
			return "OrderItems [id=" + id + ", order=" + order + ", product=" + product + ", quantity=" + quantity
					+ ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + "]";
		}
	}
