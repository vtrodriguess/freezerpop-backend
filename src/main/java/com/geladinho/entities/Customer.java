package com.geladinho.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_customer")
public class Customer extends UserBase {

	private BigDecimal balance = BigDecimal.ZERO;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	@JsonIgnore
	List<FreezerPopCustomer> freezerPops = new ArrayList<FreezerPopCustomer>();

	public Customer() {

	}

	public Customer(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = "USER";
	}

	public Customer(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public List<FreezerPopCustomer> getFreezerPops() {
		return freezerPops;
	}

	public void setFreezerPops(List<FreezerPopCustomer> freezerPops) {
		this.freezerPops = freezerPops;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
