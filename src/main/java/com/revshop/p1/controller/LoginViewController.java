package com.revshop.p1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.revshop.p1.entity.Buyer;
import com.revshop.p1.entity.Seller;
import com.revshop.p1.service.BuyerService;
import com.revshop.p1.service.SellerService;
import com.revshop.p1.service.loginService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/revshop")
public class LoginViewController {
	@Autowired
	private BuyerService buyerService;
	@Autowired
	private SellerService sellerservice;
	@GetMapping("/login")
	public String showLoginPage(Model model) 
	{
		return "login";
	}
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password,Model model,HttpSession session) {
		if(buyerService.validateBuyer(email, password)) {
			//model.addAttribute("message","loginsuccessful as buyer");
			Buyer buyer = buyerService.getBuyerByEmail(email); // Get the buyer details
            session.setAttribute("loggedInUser", buyer); // Store buyer in session
            session.setAttribute("userType", "buyer"); // Optional: Store user type for role-based handling
            return "redirect:/revshop/displayProducts";

		}
		else if(sellerservice.validateSeller(email, password)){
			Seller seller = sellerservice.getSellerByEmail(email); // Get the seller details
            session.setAttribute("loggedInUser", seller); // Store seller in session
            session.setAttribute("userType", "seller"); // Optional: Store user type for role-based handling
            return "redirect:/revshop/show"; // Redirect to seller dashboard
			}
	else {
		model.addAttribute("message","invalid");
		return "login";
	}
		
	
	}
}	
