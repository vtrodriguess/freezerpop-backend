package com.geladinho.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.geladinho.config.Token;
import com.geladinho.entities.FreezerPop;
import com.geladinho.repositories.FreezerPopRepository;

@Service
public class FreezerPopService {
	
	private FreezerPopRepository freezerPopRepository;

	public FreezerPopService(FreezerPopRepository freezerPopRepository) {
		this.freezerPopRepository = freezerPopRepository;
	}
	
	public FreezerPop createFreezerPop(String name, BigDecimal price) {
		FreezerPop create = new FreezerPop(name, price);
		freezerPopRepository.save(create);
		
		return create;
	}
	
	public List<FreezerPop> findAll(){
		return freezerPopRepository.findAll()
	            .stream()
	            .filter(x -> x.getQuantity() > 0)
	            .collect(Collectors.toList());
	}
	

}
