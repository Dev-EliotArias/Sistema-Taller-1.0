package com.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("SELECT COUNT(c) FROM Cliente c WHERE DATE(c.fechaRegistro) = CURRENT_DATE")
    long countClientesRegistradosEsteMes();
	
}
