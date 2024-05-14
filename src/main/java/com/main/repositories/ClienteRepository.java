package com.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
