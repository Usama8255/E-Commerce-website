package com.revshop.p1.service;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revshop.p1.entity.Buyer;
import com.revshop.p1.entity.Favorite;
import com.revshop.p1.entity.Product;
import com.revshop.p1.repository.FavoriteRepository;

import java.util.List;

	@Service
	public class FavoriteService {

	    @Autowired
	    private FavoriteRepository favoriteRepository;

	    @Autowired
	    private ProductService productService;

	    public List<Favorite> getFavoritesByBuyer(Buyer buyer) {
	        return favoriteRepository.findByBuyer(buyer);
	    }

	    public void addFavorite(Buyer buyer, Long productId) {
	        Product product = productService.getProductById(productId);
	        Favorite existingFavorite = favoriteRepository.findByBuyerAndProduct(buyer, product);

	        if (existingFavorite == null) {
	            Favorite favorite = new Favorite(buyer, product);
	            favoriteRepository.save(favorite);
	        }
	    }
	   
	    public void removeFavorite(Long favid) {
	    	favoriteRepository.deleteById(favid);
	        
	    }
	}



