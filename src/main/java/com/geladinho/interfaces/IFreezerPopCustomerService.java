package com.geladinho.interfaces;

import java.util.Optional;

import com.geladinho.entities.FreezerPopCustomer;

public interface IFreezerPopCustomerService {
	
	Optional <FreezerPopCustomer> createFreezerPopCustomer(Long idCustomer, Long idFreezerPop, int quantity);
}
