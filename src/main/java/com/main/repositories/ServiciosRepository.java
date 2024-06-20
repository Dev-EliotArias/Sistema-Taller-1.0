package com.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entities.Servicios;

@Repository
public interface ServiciosRepository extends JpaRepository<Servicios, Long> {
	List<Servicios> findAllById(Iterable<Long> ids);
}
