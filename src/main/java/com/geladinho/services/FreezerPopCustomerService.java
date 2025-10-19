package com.geladinho.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.geladinho.entities.Customer;
import com.geladinho.entities.FreezerPop;
import com.geladinho.entities.FreezerPopCustomer;
import com.geladinho.interfaces.IFreezerPopCustomerService;
import com.geladinho.repositories.CustomerRepository;
import com.geladinho.repositories.FreezerPopCustomerRepository;
import com.geladinho.repositories.FreezerPopRepository;

@Service
public class FreezerPopCustomerService implements IFreezerPopCustomerService{

	private FreezerPopCustomerRepository freezerPopCustomerRepository;
	private CustomerRepository customerRepository;
	private FreezerPopRepository freezerPopRepository;
	
	

	public FreezerPopCustomerService(FreezerPopCustomerRepository freezerPopCustomerRepository,
			CustomerRepository customerRepository, FreezerPopRepository freezerPopRepository) {
		this.freezerPopCustomerRepository = freezerPopCustomerRepository;
		this.customerRepository = customerRepository;
		this.freezerPopRepository = freezerPopRepository;
	}



	@Override
	public Optional<FreezerPopCustomer> createFreezerPopCustomer(Long idCustomer, Long idFreezerPop, int quantity) {
		Optional<Customer> customer = customerRepository.findById(idCustomer);
		Optional<FreezerPop> freezerPop = freezerPopRepository.findById(idFreezerPop);

		if (freezerPop.isPresent() && customer.isPresent()) {
			if (freezerPop.get().getQuantity() >= quantity) {
				FreezerPopCustomer fpc = new FreezerPopCustomer(freezerPop.get(), customer.get(), quantity);
				freezerPopCustomerRepository.save(fpc);
				return Optional.of(fpc);
			}
		}
		return Optional.empty();

	}

}
