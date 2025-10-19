package com.geladinho.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.geladinho.entities.Customer;
import com.geladinho.entities.FreezerPop;
import com.geladinho.entities.FreezerPopCustomer;
import com.geladinho.interfaces.ICustomerService;
import com.geladinho.interfaces.IFreezerPopCustomerService;
import com.geladinho.interfaces.IFreezerPopInventoryService;
import com.geladinho.interfaces.IPurchaseService;
import com.geladinho.repositories.FreezerPopCustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class PurchaseService implements IPurchaseService{

	private FreezerPopCustomerRepository freezerPopCustomerRepository;
	private IFreezerPopInventoryService freezerPopInventoryService;
	private ICustomerService customerService;
	private IFreezerPopCustomerService freezerPopCustomer;

	public PurchaseService(FreezerPopCustomerRepository freezerPopCustomerRepository,
			IFreezerPopInventoryService freezerPopInventoryService, ICustomerService customerService,
			IFreezerPopCustomerService freezerPopCustomer) {
		this.freezerPopCustomerRepository = freezerPopCustomerRepository;
		this.freezerPopInventoryService = freezerPopInventoryService;
		this.customerService = customerService;
		this.freezerPopCustomer = freezerPopCustomer;
	}

	@Override
	@Transactional
	public Optional<FreezerPopCustomer> buy(Long idCustomer, Long idFreezerPop, int quantity) {
		Optional<FreezerPopCustomer> fpc = freezerPopCustomer.createFreezerPopCustomer(idCustomer, idFreezerPop,
				quantity);

		if (fpc.isPresent()) {
			FreezerPop freezerPop = fpc.get().getFreezerPop();
			Customer customer = fpc.get().getCustomer();

			customerService.increaseBalance(freezerPop, customer, quantity);
			freezerPopInventoryService.updateFreezerPop(quantity, freezerPop);

			return Optional.of(freezerPopCustomerRepository.save(fpc.get()));
		}

		return Optional.empty();
	}

}
