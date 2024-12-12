package com.revshop.p1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.p1.entity.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long>{
	Buyer findByEmailAndPassword(String email,String password);
	Optional<Buyer> findByEmail(String email);
	
	

}
