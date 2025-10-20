package com.geladinho.dtos;

public class ChangePasswordDTO {

	private String email;
	private String newPassword;
	
	public ChangePasswordDTO() {
		
	}

	public ChangePasswordDTO(String email, String newPassword) {
		this.email = email;
		this.newPassword = newPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
