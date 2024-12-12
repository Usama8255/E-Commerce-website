package com.revshop.p1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.p1.entity.Product;
import com.revshop.p1.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository product_repo;
	
	// Add a new product
    public void addProduct(Product product) {
        product_repo.save(product);
    }
    
    // Showing products based on sellerId
    public List<Product> getAllProducts(Long id){
    	return product_repo.findBySellerId(id);
    }
    
    // Get a product by ID and Seller ID (for authorization)
    public Product getProductByIdAndSeller(Long productId, Long sellerId) {
        Optional<Product> optionalProduct = product_repo.findByIdAndSellerId(productId, sellerId);
        return optionalProduct.orElse(null);
    }

    // Update the product
    public void updateProduct(Product product) {
    	product_repo.save(product); // Save the updated product
    }
    
    // Delete a product by ID
    public void deleteProductById(Long productId) {
    	product_repo.deleteById(productId);
    }
    public List<Product> getAllProducts(){
    	return product_repo.findAll();
    }
    public Product getProductById(Long id) {
        return product_repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
	
    // search by category
    public List<Product> getProductsByCategory(String category) {
        return product_repo.findByCategory(category);
    }
    
  //search products by name
    public List<Product> getProductsByNameOrBrand(String query) {
        return product_repo.findByNameContainingOrBrandContainingIgnoreCase(query,query); 
    }

    //filter by price
    public List<Product> getProductsByDiscountPriceRange(double mindiscountPrice, double maxdiscountPrice){
    	return product_repo.findByDiscountPriceBetween(mindiscountPrice, maxdiscountPrice);
    }

}
