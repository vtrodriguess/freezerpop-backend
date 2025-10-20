package com.geladinho.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geladinho.config.Token;
import com.geladinho.dtos.CustomerDTO;
import com.geladinho.dtos.LoginResponseDTO;
import com.geladinho.entities.Customer;
import com.geladinho.interfaces.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("cliente")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

	private ICustomerService iCustomerService;
	private Token jwtUtil;

	

	public CustomerController(ICustomerService iCustomerService, Token jwtUtil) {
		this.iCustomerService = iCustomerService;
		this.jwtUtil = jwtUtil;
	}
	
	@PutMapping("/att/{id}")
	public void attBalance(@PathVariable Long id) {
		iCustomerService.attBalance(id);
	}
	
	@GetMapping("/clientes")
	public ResponseEntity<List<Customer>> findAll(@RequestHeader("Authorization") String authHeader){
		 if(authHeader == null || !authHeader.startsWith("Bearer ")) {
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		    }

		    String token = authHeader.substring(7); 
		    if(!jwtUtil.isValidToken(token)) { 
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		    }

		    return ResponseEntity.ok(iCustomerService.findAll());
		}


	@GetMapping("/balance")
	public ResponseEntity<BigDecimal> getBalance(@RequestHeader("Authorization") String authHeader) {
	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }

	    String token = authHeader.substring(7);

	    if (!jwtUtil.isValidToken(token)) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }

	    String email = jwtUtil.extractEmail(token);

	    Customer customer = iCustomerService.findByEmail(email);
	    if (customer == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }

	    return ResponseEntity.ok(customer.getBalance());
	}


	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> loginCustomer(@RequestBody Customer customer) {
		String jwt = iCustomerService.loginCustomer(customer.getEmail(), customer.getPassword());

		if (jwt != null) {
			Customer customerLogin = iCustomerService.findByEmail(customer.getEmail());
			LoginResponseDTO dto = new LoginResponseDTO(jwt, customerLogin);
			return ResponseEntity.ok(dto);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}

	@PostMapping("/cadastrar")
	public CustomerDTO createCustomer(@Valid @RequestBody Customer customer) {
		Customer create = iCustomerService.createCustomer(customer.getName(), customer.getEmail(),
				customer.getPassword());

		CustomerDTO dto = new CustomerDTO(create.getId(), create.getName(), create.getEmail());

		return dto;
	}

	@DeleteMapping("{id}")
	public void deleteById(@PathVariable Long id) {
		iCustomerService.deleteById(id);
	}
}
