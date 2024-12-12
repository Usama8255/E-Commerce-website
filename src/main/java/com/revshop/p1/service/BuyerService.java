package com.revshop.p1.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.p1.entity.Buyer;
import com.revshop.p1.repository.BuyerRepository;


@Service
public class BuyerService {
	
	@Autowired
	private BuyerRepository buyer_repo;
		
	public Buyer registerUser(Buyer buyer) {
		buyer.setRegistrationDate(LocalDateTime.now());
		return buyer_repo.save(buyer);
	}
	public boolean validateBuyer(String email, String password) {
        Buyer buyer = buyer_repo.findByEmailAndPassword(email,password);
        // Perform password validation (you may want to hash passwords in real scenarios)
        return buyer != null && buyer.getPassword().equals(password);
    }
	
	public Buyer getBuyerByEmail(String email) {
		return buyer_repo.findByEmail(email).get();
	}
	public Buyer UpdateUser(Buyer buyer) {
		buyer.setRegistrationDate(LocalDateTime.now());
		return buyer_repo.save(buyer);
	}
	
}
