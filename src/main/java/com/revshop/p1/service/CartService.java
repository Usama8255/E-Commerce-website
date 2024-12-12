package com.revshop.p1.service;

import com.revshop.p1.entity.Cart;
import com.revshop.p1.entity.Product;
import com.revshop.p1.entity.Buyer;
import com.revshop.p1.repository.CartRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductService productService;

    // Get all cart items for a buyer
    public List<Cart> getCartItemsByBuyer(Buyer buyer) {
        return cartRepository.findByBuyer(buyer);
    }


    public void addToCart(Buyer buyer, Long productId) {
        // Fetch the product by its ID
        Product product = productService.getProductById(productId);
        
        if (product != null) {
            // Check if the buyer already has this product in the cart
            Cart existingCartItem = cartRepository.findByBuyerAndProduct(buyer, product);
            
            if (existingCartItem == null) {
                // If the product isn't in the cart, create a new cart item
                Cart newCartItem = new Cart();
                newCartItem.setBuyer(buyer);
                newCartItem.setProduct(product);
                newCartItem.setQuantity(1); // Start with quantity 1
                cartRepository.save(newCartItem); // Save the new cart item
            } else {
                // If the product already exists, increment the quantity
                existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
                cartRepository.save(existingCartItem); // Update the existing cart item
            }
        }
    }
    // Remove an item from the cart
    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    // Update the cart item (e.g., change quantity)
    public void updateCartItem(Cart cart) {
        cartRepository.save(cart);
    }
    public double calculateTotalPrice(List<Cart> cartItems) {
        return cartItems.stream()
                .mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice())
                .sum();
    }
    @Transactional
    public void deletecart(Buyer buyer) {
    	cartRepository.deleteByBuyer(buyer);;
    }
}
