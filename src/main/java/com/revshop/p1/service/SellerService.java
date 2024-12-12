package com.revshop.p1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.p1.entity.Seller;
import com.revshop.p1.repository.SellerRepository;
@Service
public class SellerService {
	@Autowired
	SellerRepository repository;
	public Seller addSeller(Seller seller) {
		return repository.save(seller);
	}
	public void updateSeller(Seller seller) {
		repository.save(seller);
	}
	public boolean validateSeller(String email, String password) {
        Seller seller = repository.findByEmailAndPassword(email,password);
        return seller != null && seller.getPassword().equals(password);
    }
	
	public Seller getSellerByEmail(String email) {
		return repository.findByEmail(email).get();
	}
	
	
}
