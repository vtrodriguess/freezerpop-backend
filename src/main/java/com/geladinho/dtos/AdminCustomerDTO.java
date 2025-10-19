package com.geladinho.dtos;

import java.math.BigDecimal;

public class AdminCustomerDTO {
	
	private String name;
	private String email;
	private BigDecimal balance;
	
	public AdminCustomerDTO() {
		
	}
	
	public AdminCustomerDTO(String name, String email, BigDecimal balance) {
		this.name = name;
		this.email = email;
		this.balance = balance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	

}
