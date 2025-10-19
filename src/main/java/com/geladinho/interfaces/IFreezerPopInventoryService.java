package com.geladinho.interfaces;

import com.geladinho.entities.FreezerPop;

public interface IFreezerPopInventoryService {
	
	void updateFreezerPop(int quantity, FreezerPop fzp);
	void increaseQuantity(Long id, int quantity);

}
