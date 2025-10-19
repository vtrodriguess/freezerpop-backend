package com.geladinho.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geladinho.interfaces.IFreezerPopInventoryService;

@RestController
@RequestMapping("estoque")
@CrossOrigin(origins = "http://localhost:5173")
public class FreezerPopInventoryController {

	private IFreezerPopInventoryService freezerPopInventoryService;

	public FreezerPopInventoryController(IFreezerPopInventoryService freezerPopInventoryService) {
		this.freezerPopInventoryService = freezerPopInventoryService;
	}

	@PutMapping("/{id}/atualizar")
	public void increaseQuantity(@PathVariable Long id, @RequestParam int quantity) {
		freezerPopInventoryService.increaseQuantity(id, quantity);
	}

}
