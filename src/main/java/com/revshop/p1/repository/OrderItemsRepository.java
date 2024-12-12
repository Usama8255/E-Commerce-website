package com.revshop.p1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revshop.p1.entity.OrderItems;
@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
	 @Query("SELECT oi FROM OrderItems oi WHERE oi.order.buyer.buyer_id = :buyerId")
	    List<OrderItems> findAllByBuyerId(@Param("buyerId") Long buyerId);
}