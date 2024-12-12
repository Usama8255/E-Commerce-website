package com.revshop.p1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revshop.p1.entity.Seller;
import com.revshop.p1.service.SellerService;

@RestController
@RequestMapping("/sellers")
public class SellerController {
	@Autowired
	private SellerService service;
	@PostMapping
	public ResponseEntity<Seller>addSeller(@RequestBody Seller seller){
		service.addSeller(seller);
		return new ResponseEntity<>(seller,HttpStatus.CREATED);
	}
}
