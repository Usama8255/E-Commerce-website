package com.revshop.p1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.p1.entity.Buyer;
import com.revshop.p1.entity.Seller;
import com.revshop.p1.repository.BuyerRepository;
import com.revshop.p1.repository.SellerRepository;

@Service
public class loginService {
	@Autowired
	private BuyerRepository br;
	@Autowired
	private SellerRepository sr;
	public Buyer loginAsBuyer(String email,String password) {
		return br.findByEmailAndPassword(email, password);
	}
	public Seller loginAsSeller(String email,String password) {
		return sr.findByEmailAndPassword(email, password);
	}
	

}
