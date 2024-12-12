
package com.revshop.p1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.p1.entity.OrderItems;
import com.revshop.p1.entity.Orders;
import com.revshop.p1.repository.OrderItemsRepository;

@Service
public class OrderItemsService {
	@Autowired
	OrderItemsRepository orderitemrepo;
	@Autowired
	OrderService os;
	public void saveorderitem(OrderItems orderitem) {
		orderitemrepo.save(orderitem);
	}
//	public List<OrderItems> getOrderItemsByBuyer(Long buyerId) {
//        List<Orders> orders = os.getOrdersByBuyer(buyerId);
//        List<OrderItems> orderItems = new ArrayList<>();
//        for (Orders order : orders) {
//            orderItems.addAll(order.getOrderItems());
//        }
//        return orderItems;
//    }
	 
	    
	    public List<OrderItems> getOrderItemsByBuyerId(Long buyerId) {
	        return orderitemrepo.findAllByBuyerId(buyerId);
	    }

}
