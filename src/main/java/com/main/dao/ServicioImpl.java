package com.main.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.classDTO.ServiciosDTO;
import com.main.entities.Servicios;
import com.main.repositories.ServiciosRepository;
import com.main.services.ServicioService;

@Service
public class ServicioImpl implements ServicioService{

	
	@Autowired
	private ServiciosRepository serviciosRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ServiciosDTO> getAllServicios() {
		// TODO Auto-generated method stub
		List<Servicios> servicios = serviciosRepository.findAll();
        return servicios.stream().map(servicio -> modelMapper.map(servicio, ServiciosDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ServiciosDTO getServicioById(Long id) {
		// TODO Auto-generated method stub
		Servicios servicio = serviciosRepository.findById(id).orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        return modelMapper.map(servicio, ServiciosDTO.class);
	}

	@Override
	public ServiciosDTO createServicio(ServiciosDTO servicioDTO) {
		// TODO Auto-generated method stub
		Servicios servicio = modelMapper.map(servicioDTO, Servicios.class);
        servicio = serviciosRepository.save(servicio);
        return modelMapper.map(servicio, ServiciosDTO.class);
	}

	@Override
	public ServiciosDTO updateServicio(Long id, ServiciosDTO servicioDTO) {
		// TODO Auto-generated method stub
		Servicios servicio = serviciosRepository.findById(id).orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        servicio.setNombre(servicioDTO.getNombre());
        servicio.setCosto(servicioDTO.getCosto());
        servicio.setDescripcion(servicioDTO.getDescripcion());
        servicio.setTiempoEstimado(servicioDTO.getTiempoEstimado().intValue());
        servicio = serviciosRepository.save(servicio);
        return modelMapper.map(servicio, ServiciosDTO.class);
	}

	@Override
	public void deleteServicio(Long id) {
		// TODO Auto-generated method stub
		serviciosRepository.deleteById(id);
	}

}
