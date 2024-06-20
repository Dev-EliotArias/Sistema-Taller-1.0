package com.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.main.entities.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
	
	@Query("SELECT t FROM Tecnico t WHERE t.estado = 'ACTIVO'")
    List<Tecnico> findActiveTechnicians();

}
