package com.revshop.p1.controller;

import com.revshop.p1.entity.Cart;
import com.revshop.p1.entity.Buyer; // Ensure you have the Buyer entity imported
import com.revshop.p1.service.CartService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/revshop")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String getCartItems(HttpSession session, Model model) {
        Buyer buyer = (Buyer) session.getAttribute("loggedInUser");
        if (buyer == null) {
            return "redirect:/revshop/login";
        }

        List<Cart> cartItems = cartService.getCartItemsByBuyer(buyer);
        
        // Calculate the total price
        double totalPrice = 0.0;
        for (Cart cart : cartItems) {
            totalPrice += cart.getProduct().getDiscountPrice() * cart.getQuantity(); // Assuming getProduct() gives you the product and getPrice() returns the product's price
        }
        
        model.addAttribute("cart", cartItems);
        model.addAttribute("totalPrice", totalPrice); // Add the total price to the model
        return "cart";	
    }


    // Add a new item to the cart
    @PostMapping("/cart/add")
    public String addToCart(@ModelAttribute Cart cart, HttpSession session,@RequestParam Long productId) {
        Buyer buyer = (Buyer) session.getAttribute("loggedInUser");
        if (buyer != null) {
            cart.setBuyer(buyer); // Set the logged-in buyer
            cartService.addToCart(buyer,productId); // Call the service to add the cart item
        }
        return "redirect:/revshop/cart"; // Redirect to the cart page after adding
    }

    // Remove an item from the cart
    @GetMapping("/remove/{cartId}/{buyerId}")
    public String removeFromCart(@PathVariable Long cartId, @PathVariable Long buyerId) {
        cartService.removeFromCart(cartId);
        return "redirect:/cart/" + buyerId; // Redirect to the cart page after removal
    }

    // Update the cart item (e.g., change quantity)
    @PostMapping("/cart/update")
    public String updateCartItem(@ModelAttribute Cart cart, HttpSession session) {
        Buyer buyer = (Buyer) session.getAttribute("loggedInUser");
        if (buyer != null) {
            cart.setBuyer(buyer); // Ensure buyer is set before updating
            cartService.updateCartItem(cart); // Call the service to update the cart item
        }
        return "redirect:/revshop/cart"; // Redirect to the cart page after updating
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long cartId, HttpSession session) {
        Buyer buyer = (Buyer) session.getAttribute("loggedInUser");
        if (buyer == null) {
            return "redirect:/buyer/login";
        }

        cartService.removeFromCart(cartId); // Call the service to remove the item
        return "redirect:/revshop/cart"; // Redirect back to the cart page after removal
    }

}
