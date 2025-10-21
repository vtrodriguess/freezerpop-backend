package com.geladinho.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geladinho.config.Token;
import com.geladinho.entities.FreezerPop;
import com.geladinho.services.AdminService;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

	private AdminService admService;
	private Token jwtUtil;

	public AdminController(AdminService admService) {
		this.admService = admService;
	}
	
	@GetMapping("/all-geladinhos")
	public ResponseEntity<List<FreezerPop>> findAll(@RequestHeader("Authorization") String authHeader){
	    if(authHeader == null || !authHeader.startsWith("Bearer ")) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }

	    String token = authHeader.substring(7); 
	    if(!jwtUtil.isValidToken(token)) { 
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	   

	        return ResponseEntity.ok(admService.findAllFreezerPops());
	}

	@GetMapping("/total-vendas-mes")
	public ResponseEntity<BigDecimal> getTotalVendasMes() {
		BigDecimal total = admService.calcularTotalMesAtual();
		return ResponseEntity.ok(total);
	}
	
	@PutMapping("/{id}/atualizar")
	public void ajustaSaldoPago(@PathVariable Long id, @RequestParam BigDecimal amount){
		admService.ajustaSaldoPago(id, amount);
	}
}
