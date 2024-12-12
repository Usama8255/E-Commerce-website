package com.revshop.p1.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revshop.p1.service.forgotpasswordService;
@Controller
@RequestMapping("/revshop")
public class ForgotPasswordController {
	    @Autowired
	    private forgotpasswordService forgotPasswordService;
	    @GetMapping("/forgotpassword")
	    public String showForgotPasswordPage() {
	        return "forgotpassword"; // Return the Thymeleaf template for forgot password page
	    }


	    @PostMapping("/forgotpassword/sendotp")
	    public String sendOtp(@RequestParam("email") String email, Model model) {
	        String userType = forgotPasswordService.sendOtpToUser(email);

	        if (userType != null) {
	            model.addAttribute("email", email); // Store email for OTP verification
	            return "forgotpassword"; // Return to the same template to show OTP input
	        } else {
	            model.addAttribute("error", "Email not found.");
	            return "forgotpassword"; // Return to the same template with an error message
	        }
	    }

	    @PostMapping("/forgotpassword/verifyotp")
	    public String verifyOtp(@RequestParam("email") String email, @RequestParam("otp") String otp, Model model) {
	        if (forgotPasswordService.validateOtp(email, otp)) {
	            model.addAttribute("otpVerified", true); // Indicate OTP verification success
	            model.addAttribute("email", email);
	            model.addAttribute("otp",otp);
	            return "forgotpassword"; // Return to the same template to show reset password input
	        } else {
	            model.addAttribute("error", "Invalid OTP.");
	            model.addAttribute("email", email); // Retain email for re-entering OTP
	            return "forgotpassword"; // Return to the same template with an error message
	        }
	    }

	    @PostMapping("/forgotpassword/resetpassword")
	    public String resetPassword(@RequestParam("email") String email,
	                                 @RequestParam("newPassword") String newPassword,
	                                 @RequestParam("confirmPassword") String confirmPassword,
	                                 @RequestParam("otp") String otp,
	                                 Model model) {
	        if (!newPassword.equals(confirmPassword)) {
	            model.addAttribute("error", "Passwords do not match.");
	            model.addAttribute("email", email);
	            model.addAttribute("otp",otp);
	            
	            return "forgotpassword"; // Return to the same template with an error message
	        }

	        forgotPasswordService.resetPassword(email, newPassword);
	        return "redirect:/revshop/login"; // Redirect to the login page after successful reset
	    }
	    
	}



