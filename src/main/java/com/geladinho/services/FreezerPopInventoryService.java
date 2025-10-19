package com.geladinho.services;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.geladinho.entities.FreezerPop;
import com.geladinho.interfaces.IFreezerPopInventoryService;
import com.geladinho.repositories.FreezerPopRepository;

@Service
public class FreezerPopInventoryService implements IFreezerPopInventoryService{

	private FreezerPopRepository freezerPopRepository;

	public FreezerPopInventoryService(FreezerPopRepository freezerPopRepository) {
		this.freezerPopRepository = freezerPopRepository;
	}

	@Override
	public void updateFreezerPop(int quantity, FreezerPop fzp) {
		fzp.setQuantity(fzp.getQuantity() - quantity);
		freezerPopRepository.save(fzp);
	}
	
	@Override
	public void increaseQuantity(Long id, int quantity) {
		Optional<FreezerPop> fzp = freezerPopRepository.findById(id);
		
		if(fzp.isPresent()) {
			fzp.get().setQuantity(fzp.get().getQuantity() + quantity);
			freezerPopRepository.save(fzp.get());
		}
	}
	

}
