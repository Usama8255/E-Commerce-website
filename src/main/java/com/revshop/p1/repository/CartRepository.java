package com.revshop.p1.repository;

import com.revshop.p1.entity.Buyer;
import com.revshop.p1.entity.Cart;
import com.revshop.p1.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    
    List<Cart> findByBuyer(Buyer buyer);
    Cart findByBuyerAndProduct(Buyer buyer, Product product);
        void deleteByBuyer(Buyer buyer);
    

    
}
