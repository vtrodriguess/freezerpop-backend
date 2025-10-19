package com.geladinho.services;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.geladinho.entities.Customer;
import com.geladinho.repositories.CustomerRepository;
import com.geladinho.repositories.FreezerPopCustomerRepository;

@Service
public class AdminService {
	
	private FreezerPopCustomerRepository fpc;
	private CustomerRepository customerRepository;

	public AdminService(FreezerPopCustomerRepository fpc, CustomerRepository customerRepository) {
		this.fpc = fpc;
		this.customerRepository = customerRepository;
	}

	public BigDecimal calcularTotalMesAtual() {
        LocalDate today = LocalDate.now();
        BigDecimal total = fpc.totalVendidoNoMes(today.getMonthValue(), today.getYear());
        return total != null ? total : BigDecimal.ZERO;
    }
	
	public void ajustaSaldoPago(Long id, BigDecimal amount) {
		Customer customer = customerRepository.findById(id).get();
		customer.setBalance(customer.getBalance().subtract(amount));
		
		customerRepository.save(customer);
	}
	
	

}
