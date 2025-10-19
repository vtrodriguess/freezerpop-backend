package com.geladinho.dtos;

import com.geladinho.entities.Customer;

public class LoginResponseDTO {
	
	private String token;
	private Customer customer;

	public LoginResponseDTO(String token, Customer customer) {
		this.token = token;
		this.customer = customer;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	

}
