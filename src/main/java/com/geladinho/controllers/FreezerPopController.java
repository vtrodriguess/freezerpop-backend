package com.geladinho.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geladinho.config.Token;
import com.geladinho.entities.FreezerPop;
import com.geladinho.services.FreezerPopService;

@RestController
@RequestMapping("geladinho")
@CrossOrigin(origins = "http://localhost:5173")
public class FreezerPopController {

	private FreezerPopService freezerPopService;
	private Token jwtUtil;
	
	public FreezerPopController(FreezerPopService freezerPopService, Token jwtUtil) {
		this.freezerPopService = freezerPopService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/cadastrar")
	public FreezerPop createFreezerPop(@RequestBody FreezerPop freezerPop) {
		FreezerPop fzp = freezerPopService.createFreezerPop(freezerPop.getFlavor(), freezerPop.getPrice());
		
		return fzp;
	}
	
	@GetMapping("/geladinhos")
	public ResponseEntity<List<FreezerPop>> findAll(@RequestHeader("Authorization") String authHeader){
	    if(authHeader == null || !authHeader.startsWith("Bearer ")) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }

	    String token = authHeader.substring(7); 
	    if(!jwtUtil.isValidToken(token)) { 
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }

	    return ResponseEntity.ok(freezerPopService.findAll());
	}
	
}
