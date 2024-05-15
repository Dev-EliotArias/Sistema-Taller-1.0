package com.main.services;

import java.util.List;

import com.main.classDTO.OrdenTrabajoDTO;

public interface OrdenTrabajoService {
	
	List<OrdenTrabajoDTO> getAllOrdenesTrabajo();
    OrdenTrabajoDTO getOrdenTrabajoById(Long id);
    OrdenTrabajoDTO createOrdenTrabajo(OrdenTrabajoDTO ordenTrabajoDTO);
    OrdenTrabajoDTO updateOrdenTrabajo(Long id, OrdenTrabajoDTO ordenTrabajoDTO);
    void deleteOrdenTrabajo(Long id);
	
}
