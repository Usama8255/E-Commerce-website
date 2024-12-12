package com.revshop.p1.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.p1.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
		
	public Optional<Product> findById(Long id);
	List<Product> findBySellerId(Long id);
	
	// Update Product By ProductId and SellerId
	Optional<Product> findByIdAndSellerId(Long id, Long sellerId);
	
	// search by category
		List<Product> findByCategory(String category);
		
		//search products by name
		List<Product> findByNameContainingOrBrandContainingIgnoreCase(String name, String brand);
		
		//filter by price
		List<Product> findByDiscountPriceBetween(double mindiscountPrice, double maxdiscountPrice);
		

	
	
}
