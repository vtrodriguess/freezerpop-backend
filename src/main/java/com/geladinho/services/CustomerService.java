package com.geladinho.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.geladinho.config.Token;
import com.geladinho.entities.Customer;
import com.geladinho.entities.FreezerPop;
import com.geladinho.interfaces.ICustomerService;
import com.geladinho.repositories.CustomerRepository;

@Service
public class CustomerService implements ICustomerService {

	private CustomerRepository customerRepository;
	private BCryptPasswordEncoder passwordEncoder;
	private Token token;

	public CustomerService(CustomerRepository customerRepository, BCryptPasswordEncoder passwordEncoder, Token token) {
		this.customerRepository = customerRepository;
		this.passwordEncoder = passwordEncoder;
		this.token = token;
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public String loginCustomer(String email, String senha) {
		Optional<Customer> customer = customerRepository.findByEmail(email);

		if (customer.isPresent()) {
			boolean password = passwordEncoder.matches(senha, customer.get().getPassword());
			if (password) {
				return token.generateToken(customer.get());
			}

		}

		return null;

	}
	
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email).get();
	}

	@Override
	public Customer createCustomer(String name, String email, String senha) {
		String pass = passwordEncoder.encode(senha);
		Customer customer = new Customer(name, email, pass);
	
		customerRepository.save(customer);

		return customer;
	}

	@Override
	public void increaseBalance(FreezerPop freezerPop, Customer customer, int quantity) {
		BigDecimal total = freezerPop.getPrice().multiply(BigDecimal.valueOf(quantity));
		customer.setBalance(customer.getBalance().add(total));

		customerRepository.save(customer);
	}
	
	@Override
	public void attBalance(Long id) {
		Customer customer = customerRepository.findById(id).get();
		customer.setBalance(BigDecimal.ZERO);
		customerRepository.save(customer);
	}

	@Override
	public void deleteById(Long id) {
		customerRepository.deleteById(id);
		;
	}

}
