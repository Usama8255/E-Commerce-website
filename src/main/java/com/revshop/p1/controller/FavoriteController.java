package com.revshop.p1.controller;


	import com.revshop.p1.entity.Buyer;
	import com.revshop.p1.entity.Favorite;
	import com.revshop.p1.service.FavoriteService;
	import jakarta.servlet.http.HttpSession;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.*;

	import java.util.List;

	@Controller
	@RequestMapping("/revshop")
	public class FavoriteController {

	    @Autowired
	    private FavoriteService favoriteService;

	    // Display all favorite products for the logged-in buyer
	    @GetMapping("/favorite")
	    public String showFavorites(Model model, HttpSession session) {
	        Buyer buyer = (Buyer) session.getAttribute("loggedInUser");
	        if (buyer == null) {
	            return "redirect:/revshop/login"; 
	        }

	        List<Favorite> favorites = favoriteService.getFavoritesByBuyer(buyer);
	        model.addAttribute("favorites", favorites);
	        return "favorite";  // This is the Thymeleaf view name for displaying favorites
	    }

	    @PostMapping("/favorites/add")
	    public String addFavorite(@RequestParam Long productId, HttpSession session) {
	        Buyer buyer = (Buyer) session.getAttribute("loggedInUser");
	        if (buyer == null) {
	            return "redirect:/revshop/login"; 
	        }

	        favoriteService.addFavorite(buyer, productId);
	        return "redirect:/revshop/displayProducts"; 
	    }

	    @PostMapping("/favorite/remove")
	    public String removeFavorite(@RequestParam Long favoriteId, HttpSession session) {
	        Buyer buyer = (Buyer) session.getAttribute("loggedInUser");
	        if (buyer == null) {
	            return "redirect:/revshop/login"; 
	        }
	        System.out.println(favoriteId);

	        favoriteService.removeFavorite(favoriteId);
	        return "redirect:/revshop/favorite"; // Redirect to the favorites page
	    }
	}


