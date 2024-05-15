package com.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entities.Servicios;

@Repository
public interface ServiciosRepository extends JpaRepository<Servicios, Long> {

}
