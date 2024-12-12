package com.revshop.p1.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.revshop.p1.entity.Buyer;
import com.revshop.p1.entity.Seller;
import com.revshop.p1.repository.BuyerRepository;
import com.revshop.p1.repository.SellerRepository;
@Service
public class forgotpasswordService {
	    private Map<String, String> otpStore = new HashMap<>(); 

	    @Autowired
	    private BuyerRepository buyerRepository;

	    @Autowired
	    private SellerRepository sellerRepository;

	    @Autowired
	    private JavaMailSender mailSender;

	    public String generateOtp() {
	        Random random = new Random();
	        return String.valueOf(100000 + random.nextInt(900000)); // Generate 6-digit OTP
	    }

	    public String sendOtpToUser(String email) {
	        Optional<Buyer> buyer = buyerRepository.findByEmail(email);
	        Optional<Seller> seller = sellerRepository.findByEmail(email);

	        if (buyer.isPresent()) {
	            String otp = generateOtp();
	            otpStore.put(email, otp); // Store OTP in memory
	            sendEmail(email, "Reset Password OTP", "Your OTP is: " + otp);
	            return "buyer"; // Indicate that it's a buyer
	        } else if (seller.isPresent()) {
	            String otp = generateOtp();
	            otpStore.put(email, otp); // Store OTP in memory
	            sendEmail(email, "Reset Password OTP", "Your OTP is: " + otp);
	            return "seller"; // Indicate that it's a seller
	        }

	        return null; // Email not found
	    }

	    public void sendEmail(String to, String subject, String text) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(text);
	        mailSender.send(message);
	    }

	    public boolean validateOtp(String email, String otp) {
	        return otpStore.containsKey(email) && otpStore.get(email).equals(otp);
	    }

	    public void resetPassword(String email, String newPassword) {
	        Optional<Buyer> buyer = buyerRepository.findByEmail(email);
	        Optional<Seller> seller = sellerRepository.findByEmail(email);

	        if (buyer.isPresent()) {
	            buyer.get().setPassword(newPassword);
	            buyerRepository.save(buyer.get());
	        } else if (seller.isPresent()) {
	            seller.get().setPassword(newPassword);
	            sellerRepository.save(seller.get());
	        }

	        otpStore.remove(email); // Clear OTP after reset
	    }
	}



