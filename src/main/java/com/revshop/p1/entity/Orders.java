
package com.revshop.p1.entity;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "orders")
public class Orders {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @ManyToOne
	    @JoinColumn(name = "buyer_id", nullable = false)
	    private Buyer buyer;
	    private double totalPrice;

	    private String shippingAddress;

	    private String paymentMethod;

	    // Optional, only applicable if UPI is chosen

	    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    private List<OrderItems> orderItems;
	    
	    // Constructors, getters, setters
	    public Orders() {}

	    
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Buyer getBuyer() {
			return buyer;
		}

		public void setBuyer(Buyer buyer) {
			this.buyer = buyer;
		}

		public double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}

		public String getShippingAddress() {
			return shippingAddress;
		}

		public void setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
		}

		public String getPaymentMethod() {
			return paymentMethod;
		}

		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}

		
		public List<OrderItems> getOrderItems() {
			return orderItems;
		}

		public void setOrderItems(List<OrderItems> orderItems) {
			this.orderItems = orderItems;
		}
	    

}

	
