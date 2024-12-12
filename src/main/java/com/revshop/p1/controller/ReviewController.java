
package com.revshop.p1.controller;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.*;
	import org.springframework.ui.Model;

	import com.revshop.p1.entity.Buyer;
	import com.revshop.p1.entity.Product;
	import com.revshop.p1.entity.Review;
	import com.revshop.p1.service.BuyerService;
	import com.revshop.p1.service.ProductService;
	import com.revshop.p1.service.ReviewService;


	import jakarta.servlet.http.HttpSession;

	@Controller
	@RequestMapping("/revshop")
	public class ReviewController {

	    @Autowired
	    private ReviewService reviewService;
	    
	    @Autowired
	    private ProductService productService;

	    @Autowired
	    private BuyerService buyerService;
	    @GetMapping("/addreview")
	    public String addReviewForm(@RequestParam Product productId,
	                                Model model) {
	        model.addAttribute("productId", productId);
	        return "reviews";
	    }
	    
	    @PostMapping("/addreview")
	    public String addReview(@RequestParam long productId,
	                            HttpSession session,
	                            @RequestParam String comment) {
	        Buyer buyer = (Buyer) session.getAttribute("loogedInUser");
	        
	        Product product = productService.getProductById(productId);
	        Review review = new Review();
	        
	        review.setProduct(product);
	        review.setReviewText(comment);
	        
	        // Update this to use the review object directly
	        reviewService.addReview(review); // Assuming the service method takes a Review object

	        return "redirect:/revshop/displayProducts";  // Redirect to product reviews page
	    }


	    @GetMapping("/{productId}")
	    public String viewProductReviews(@PathVariable Long productId, Model model) {
	        model.addAttribute("reviews", reviewService.getReviewsByProductId(productId));
	        return "product-reviews";
	    }

	    /*
	    @GetMapping("/add/{productId}/{buyerId}")
	    public String testGetMethod(@PathVariable Long productId, @PathVariable Long buyerId,HttpSession session,Model model) {
	    	  Buyer buyer = (Buyer) session.getAttribute("loggedInUser"); // Make sure the key matches the one used in your session

			    if (buyer == null) {
			        model.addAttribute("message", "You need to log in to place an order.");
			        return "redirect:/revshop/login"; // Redirect to login if buyer is not found in session
			    }
	        return "orderitems";
	    }
	    
	    @PostMapping("/add/{productId}/{buyerId}")
	    public String addReview(
	        @PathVariable Long productId, 
	        @PathVariable Long buyerId, 
	        @RequestParam String reviewText, HttpSession session, Model model) {
	    	  Buyer buyer = (Buyer) session.getAttribute("loggedInUser"); // Make sure the key matches the one used in your session

			    if (buyer == null) {
			        model.addAttribute("message", "You need to log in to place an order.");
			        return "redirect:/revshop/login"; // Redirect to login if buyer is not found in session
			    }
	        
	        reviewService.addReview(productId, buyerId, reviewText);
	        return "redirect:/revshop/orderitems";
	    } */
	    
	}


