package com.revshop.p1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.revshop.p1.entity.Buyer;
import com.revshop.p1.service.BuyerService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/revshop")
public class BuyerController {
	
	@Autowired
	private BuyerService buyerService;
	
	@GetMapping("/buyerRegister")
	public String showRegForm(Model model) {
		model.addAttribute("buyers", new Buyer());
		return "buyerRegister";
	}
	 @GetMapping("/update")
	    public String showUpdateForm(HttpSession session, Model model) {
	        Buyer buyer = (Buyer) session.getAttribute("loggedInUser");
	        if (buyer != null) {
	            model.addAttribute("buyers", buyer); 
	            return "updateForm"; 
	        }
	        return "redirect:/revshop/login"; // Redirect to login if buyer is not in session
	    }

	    @PostMapping("/update")
	    public String updateBuyer(HttpSession session, Buyer updatedBuyer,RedirectAttributes redirectAttributes) {
	       
	        Buyer existingBuyer = (Buyer) session.getAttribute("loggedInUser");
	        if (existingBuyer != null) {
	            existingBuyer.setFirstName(updatedBuyer.getFirstName());
	            existingBuyer.setLastName(updatedBuyer.getLastName());
	            existingBuyer.setPhoneNumber(updatedBuyer.getPhoneNumber());
	            existingBuyer.setPassword(updatedBuyer.getPassword());
	            
	            buyerService.UpdateUser(existingBuyer) ;

	            
	            session.setAttribute("buyer", existingBuyer);
	            redirectAttributes.addFlashAttribute("message", "Profile updated successfully!");
	        }
	        return "redirect:/revshop/displayProducts"; 
	    }
	@PostMapping("/buyerRegister")
	public String registerBuyer(Model model, @ModelAttribute Buyer buyer) {
		buyerService.registerUser(buyer);
		return "redirect:/revshop/login";
	}
	
	@GetMapping("/buyerLogin")
	public String showLoginForm(Model model) {
		model.addAttribute("buyers", new Buyer());
		return "showProducts";
	}
	@PostMapping("/buyerUpdate")
	public String updateBuyer(Model model, @ModelAttribute Buyer buyer) {
		buyerService.registerUser(buyer);
		return "redirect:/revshop/login";
	}
	
	

}
