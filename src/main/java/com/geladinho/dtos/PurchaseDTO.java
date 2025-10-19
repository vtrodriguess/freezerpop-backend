package com.geladinho.dtos;

public class PurchaseDTO {
	
	private Long idCustomer;
	private Long idFreezerPop;
	private int quantity;
	
	public PurchaseDTO() {
		
	}

	public PurchaseDTO(Long idCustomer, Long idFreezerPop, int quantity) {
		this.idCustomer = idCustomer;
		this.idFreezerPop = idFreezerPop;
		this.quantity = quantity;
	}

	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Long getIdFreezerPop() {
		return idFreezerPop;
	}

	public void setIdFreezerPop(Long idFreezerPop) {
		this.idFreezerPop = idFreezerPop;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
