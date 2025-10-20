package com.geladinho.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class UserBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@NotBlank(message = "O nome é obrigatório")
	protected String name;
	
	@NotBlank(message = "O e-mail é obrigatório")
	protected String email;
	
	@NotBlank(message = "A senha é obrigatória")
	@Size(min = 8, message = "A senha deve ter pelo menos 6 caracteres")
	protected String password;
	 
	protected String role;

	public UserBase() {

	}

	public UserBase(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}
