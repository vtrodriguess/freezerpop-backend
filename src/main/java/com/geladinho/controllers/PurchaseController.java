package com.geladinho.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geladinho.dtos.PurchaseDTO;
import com.geladinho.entities.FreezerPopCustomer;
import com.geladinho.interfaces.IPurchaseService;

@RestController
@RequestMapping("comprar")
@CrossOrigin(origins = "http://localhost:5173")
public class PurchaseController {
	
	private IPurchaseService purchaseService;

	public PurchaseController(IPurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	
	@PostMapping
	public PurchaseDTO buy(@RequestBody PurchaseDTO dto){
		Optional<FreezerPopCustomer> fzp = purchaseService.buy(dto.getIdCustomer(), dto.getIdFreezerPop(), dto.getQuantity());
		
		PurchaseDTO purchase = new PurchaseDTO(fzp.get().getCustomer().getId(), fzp.get().getFreezerPop().getId(), fzp.get().getQuantity());
		
		return purchase;
	}

}
