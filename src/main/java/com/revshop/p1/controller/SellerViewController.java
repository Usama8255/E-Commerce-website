package com.revshop.p1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revshop.p1.entity.Seller;
import com.revshop.p1.service.SellerService;
@Controller
@RequestMapping("/revshop")
public class SellerViewController {
	
		@Autowired
		private SellerService service;
		
		@GetMapping("/register")
		public String showForm(Model model) {
			model.addAttribute("sellers", new Seller());
			return "SellersReg";
			
		}
		@PostMapping("/register")
		public String registerSeller(Model model,@ModelAttribute Seller seller ) {
			service.addSeller(seller);
			return "login";
		}
		

	}

