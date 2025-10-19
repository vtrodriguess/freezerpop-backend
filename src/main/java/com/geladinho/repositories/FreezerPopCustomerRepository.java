package com.geladinho.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geladinho.entities.FreezerPopCustomer;

public interface FreezerPopCustomerRepository extends JpaRepository<FreezerPopCustomer, Long> {

	@Query("SELECT SUM(fpc.freezerPop.price * fpc.quantity) " +
	           "FROM FreezerPopCustomer fpc " +
	           "WHERE MONTH(fpc.data) = :mes AND YEAR(fpc.data) = :ano")
	    BigDecimal totalVendidoNoMes(@Param("mes") int mes, @Param("ano") int ano);
}
