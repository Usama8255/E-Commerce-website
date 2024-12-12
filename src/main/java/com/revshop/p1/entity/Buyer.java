package com.revshop.p1.entity;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;

@Entity
public class Buyer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long buyer_id;
	
	@NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(nullable = false, length = 100, unique = true) // Max length and not null constraint
    private String email;  // Buyer's email for login and notifications

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 100, message = "Password should be between 8 and 100 characters")
    @Column(nullable = false)
    private String password;  // Encrypted password

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name should not exceed 50 characters")
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name should not exceed 50 characters")
    @Column(nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "Phone number is required")
    @Size(max = 15, message = "Phone number should not exceed 15 characters")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number should be valid")
    @Column(nullable = false, length = 15)
    private String phoneNumber;  // Buyer's contact number
    
    @NotNull
    private LocalDateTime registrationDate;  
    // Account creation date
    @OneToMany(mappedBy="buyer" ,cascade = CascadeType.ALL)
    private List<Orders> orders;

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public Long getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(Long buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}
	
   
}
