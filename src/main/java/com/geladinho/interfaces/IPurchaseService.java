package com.geladinho.interfaces;

import java.util.Optional;

import com.geladinho.entities.FreezerPopCustomer;

public interface IPurchaseService {
	
	Optional<FreezerPopCustomer> buy(Long idCustomer, Long idFreezerPop, int quantity);

}
