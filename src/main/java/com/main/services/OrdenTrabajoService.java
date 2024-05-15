package com.main.services;

import java.util.List;
import java.util.Optional;

import com.main.classDTO.OrdenTrabajoDTO;

public interface OrdenTrabajoService {
	
	List<OrdenTrabajoDTO> getAllOrdenesTrabajo();
    Optional<OrdenTrabajoDTO> getOrdenTrabajoById(Long id);
    Optional<OrdenTrabajoDTO> createOrdenTrabajo(OrdenTrabajoDTO nuevaOrdenTrabajoDTO);
    OrdenTrabajoDTO updateOrdenTrabajo(Long id, OrdenTrabajoDTO actualizarOrdenTrabajoDTO);
    void deleteOrdenTrabajo(Long id);
	
}
