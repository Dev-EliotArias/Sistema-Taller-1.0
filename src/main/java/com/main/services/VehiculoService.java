package com.main.services;

import java.util.List;

import com.main.classDTO.VehiculoDTO;
import com.main.exceptions.ClienteNotFoundException;

public interface VehiculoService {
	
	List<VehiculoDTO> getAllVehiculos();
    VehiculoDTO getVehiculoById(Long id) throws ClienteNotFoundException;
    VehiculoDTO createVehiculo(Long idCliente,VehiculoDTO vehiculoDTO) throws ClienteNotFoundException;
    VehiculoDTO updateVehiculo(Long id, VehiculoDTO vehiculoDTO) throws ClienteNotFoundException;
    void deleteVehiculo(Long id);
}
