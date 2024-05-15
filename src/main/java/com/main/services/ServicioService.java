package com.main.services;

import java.util.List;

import com.main.classDTO.ServiciosDTO;

public interface ServicioService {

	List<ServiciosDTO> getAllServicios();
    ServiciosDTO getServicioById(Long id);
    ServiciosDTO createServicio(ServiciosDTO servicioDTO);
    ServiciosDTO updateServicio(Long id, ServiciosDTO servicioDTO);
    void deleteServicio(Long id);
	
}
