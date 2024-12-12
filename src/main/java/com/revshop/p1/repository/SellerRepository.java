package com.revshop.p1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.p1.entity.Seller;
@Repository
public interface SellerRepository extends JpaRepository<Seller, Long>{
	Seller findByEmailAndPassword(String email,String password);
	Optional<Seller> findByEmail(String email);
}
