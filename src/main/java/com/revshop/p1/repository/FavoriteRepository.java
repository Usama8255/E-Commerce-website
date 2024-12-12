package com.revshop.p1.repository;
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;

import com.revshop.p1.entity.Buyer;
import com.revshop.p1.entity.Favorite;
import com.revshop.p1.entity.Product;

import java.util.List;
import java.util.Optional;

	@Repository
	public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
	    List<Favorite> findByBuyer(Buyer buyer);
	    Favorite findByBuyerAndProduct(Buyer buyer, Product product);
	}

