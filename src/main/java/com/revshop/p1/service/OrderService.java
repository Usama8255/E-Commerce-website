package com.revshop.p1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revshop.p1.entity.Buyer;
import com.revshop.p1.entity.OrderItems;
import com.revshop.p1.entity.Orders;
import com.revshop.p1.repository.OrderItemsRepository;
import com.revshop.p1.repository.OrderRepository;

import java.util.List;
	import java.util.Optional;

	@Service
	@Transactional
	public class OrderService {

	    @Autowired
	    private OrderRepository orderRepository;
	    @Autowired
	    private OrderItemsRepository orderitemsrepository;

	    // Method to create a new order
	    public Orders createOrder(Orders order) {
	        return orderRepository.save(order);
	    }

	    
//	    public List<Orders> getOrdersByBuyer(Long buyerId) {
//	        List<Orders> orders = orderRepository.findByBuyerIdWithItems(buyerId);
//
//	        // Ensure that orderItems are fetched if using LAZY loading
//	        for (Orders order : orders) {
//	            order.getOrderItems();  // Forces initialization of orderItems
//	        }
//
//	        return orders;
//	    }
	public List<OrderItems> getOrderItemsByBuyerId(Long buyerId) {
        return orderRepository.findAllOrderItemsByBuyerId(buyerId);
    }

	    

	    // Method to get an order by its ID
	    
	    // Method to delete an order
	    public void deleteOrder(Long orderId) {
	        if (orderRepository.existsById(orderId)) {
	            orderRepository.deleteById(orderId);
	        } else {
	            throw new RuntimeException("Order not found with ID: " + orderId);
	        }
	    }
	    
	    public List<Orders> getOrdersBySeller(Long sellerId){
	    	return orderRepository.findOrdersBySellerId(sellerId);
	    }
	   
	}