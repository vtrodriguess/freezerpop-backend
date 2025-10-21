package com.geladinho.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.geladinho.entities.Customer;
import com.geladinho.entities.FreezerPop;
import com.geladinho.repositories.CustomerRepository;
import com.geladinho.repositories.FreezerPopCustomerRepository;
import com.geladinho.repositories.FreezerPopRepository;

@Service
public class AdminService {
	
	private FreezerPopCustomerRepository fpc;
	private CustomerRepository customerRepository;
	private FreezerPopRepository freezerPopRepository;

	public AdminService(FreezerPopCustomerRepository fpc, CustomerRepository customerRepository,
			FreezerPopRepository freezerPopRepository) {
		this.fpc = fpc;
		this.customerRepository = customerRepository;
		this.freezerPopRepository = freezerPopRepository;
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
	
	public List<FreezerPop> findAllFreezerPops(){
		return freezerPopRepository.findAll();
	}
	
	

}
