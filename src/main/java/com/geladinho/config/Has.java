package com.geladinho.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Has {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senha = "admin123"; 
        String hash = encoder.encode(senha);
        System.out.println("Hash gerado: " + hash);

	}

}
