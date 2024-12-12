package com.revshop.p1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revshop.p1.entity.Review;

public interface ReviewRepository  extends JpaRepository<Review, Long>{
	 List<Review> findByProductId(Long productId);

}
