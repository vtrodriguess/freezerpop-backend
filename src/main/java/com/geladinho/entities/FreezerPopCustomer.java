package com.geladinho.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_freezerpop_customer")
public class FreezerPopCustomer {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	private Customer customer;

	@ManyToOne
	private FreezerPop freezerPop;
	
	@CreationTimestamp
	private Date data;
	
	private int quantity;
	
	public FreezerPopCustomer() {
		
	}

	public FreezerPopCustomer(FreezerPop freezerPop, Customer customer, int quantity) {
		this.customer = customer;
		this.freezerPop = freezerPop;
		this.quantity = quantity;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public FreezerPop getFreezerPop() {
		return freezerPop;
	}

	public void setFreezerPop(FreezerPop freezerPop) {
		this.freezerPop = freezerPop;
	}
	
	
	

}
