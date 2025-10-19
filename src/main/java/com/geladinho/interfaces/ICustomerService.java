package com.geladinho.interfaces;

import java.util.List;

import com.geladinho.dtos.CustomerDTO;
import com.geladinho.entities.Customer;
import com.geladinho.entities.FreezerPop;

public interface ICustomerService {
	
	Customer createCustomer(String name, String email, String senha);
	void increaseBalance(FreezerPop freezerPop, Customer customer, int quantity);
	void deleteById(Long id);
	String loginCustomer(String email, String senha);
	Customer findByEmail(String email);
	List<Customer> findAll();
	void attBalance(Long id);

}
