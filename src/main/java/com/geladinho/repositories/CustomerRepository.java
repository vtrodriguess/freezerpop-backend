package com.geladinho.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geladinho.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Optional<Customer> findByEmail(String email);
}
