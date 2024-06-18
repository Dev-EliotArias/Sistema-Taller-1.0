package com.main.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.classDTO.ClienteDTO;
import com.main.classDTO.OrdenTrabajoDTO;
import com.main.classDTO.ServiciosDTO;
import com.main.classDTO.TecnicoDTO;
import com.main.classDTO.VehiculoDTO;
import com.main.entities.OrdenTrabajo;
import com.main.entities.Servicios;
import com.main.entities.Tecnico;
import com.main.entities.Vehiculo;
import com.main.exceptions.NotFoundException;
import com.main.repositories.OrdenTrabajoRepository;
import com.main.repositories.ServiciosRepository;
import com.main.repositories.TecnicoRepository;
import com.main.repositories.VehiculoRepository;
import com.main.services.OrdenTrabajoService;

@Service
public class OrdenTrabajoImpl implements OrdenTrabajoService {

	@Autowired
	private OrdenTrabajoRepository ordenTrabajoRepository;
	
	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ServiciosRepository serviciosRepository;	
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<OrdenTrabajoDTO> getAllOrdenesTrabajo() {
		// TODO Auto-generated method stub
		List<OrdenTrabajo> ordenesTrabajo = ordenTrabajoRepository.findAll();
		List<OrdenTrabajoDTO> ordenTrabajoDTOs;
		
		return ordenesTrabajo.stream()
                .map(ordenTrabajo -> convertToDTO(ordenTrabajo))
                .collect(Collectors.toList());
	}

	@Override
	public Optional<OrdenTrabajoDTO> getOrdenTrabajoById(Long id) {
		// TODO Auto-generated method stub
		//OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(id).orElse(null);
		//return ordenTrabajo != null ? convertToDTO(ordenTrabajo) : null;
		
		return Optional.ofNullable(ordenTrabajoRepository.findById(id))
		        .map(ordenTrabajoOptional -> {
		        	if (ordenTrabajoOptional.isPresent()) {
		        		System.out.println(ordenTrabajoOptional.get().getEstado());
						return modelMapper.map(ordenTrabajoOptional.get(), OrdenTrabajoDTO.class);						
					}else {
						System.out.println("Tu webada no sirvio");
						return null;
					}
		        });		
	}

	@Override
	public Optional<OrdenTrabajoDTO> createOrdenTrabajo(OrdenTrabajoDTO nuevaOrdenTrabajoDTO) {
			
		Vehiculo vehiculoOrdenT = vehiculoRepository.findById(nuevaOrdenTrabajoDTO.getVehiculoId()).orElseThrow(() -> new NotFoundException("Vehiculo not found"));
	    List<Servicios> serviciosOrdenT = serviciosRepository.findAllById(nuevaOrdenTrabajoDTO.getServiciosId().stream().collect(Collectors.toList()));	    
	    Tecnico tecnicoOrdenT = tecnicoRepository.findById(nuevaOrdenTrabajoDTO.getTecnicoId()).orElseThrow(() -> new NotFoundException("Tecnico not found"));

		
		if (nuevaOrdenTrabajoDTO == null || vehiculoOrdenT == null || tecnicoOrdenT == null || serviciosOrdenT.isEmpty()) {
			return null;
		}
		
		//Vehiculo vehiculo = vehiculoRepository.findById(nuevaOrdenTrabajoDTO.getVehiculo().getId()).orElseThrow(() -> new NotFoundException("Vehiculo not found"));
	    
	    
	    //OrdenTrabajo existingOrder = ordenTrabajoRepository.findByVehiculoAndServiciosAndTecnico(vehiculo, servicios, tecnico);
	    //if (existingOrder!= null) {
	    //    throw new ConflictException("OrdenTrabajo already exists");
	    //}
	    
	    OrdenTrabajo nuevaOrdenTrabajo = OrdenTrabajo.builder()
	            .vehiculo(vehiculoOrdenT)
	            .servicios(serviciosOrdenT)
	            .tecnico(tecnicoOrdenT)
	            .fechaIngreso(nuevaOrdenTrabajoDTO.getFechaIngreso())
	            .fechaSalida(nuevaOrdenTrabajoDTO.getFechaSalida())
	            .costoTotal(nuevaOrdenTrabajoDTO.getCostoTotal())
	            .comentario(nuevaOrdenTrabajoDTO.getComentario())
	            .estado(nuevaOrdenTrabajoDTO.getEstado())
	            .build();
	    
	    OrdenTrabajo ordenTrabajoGuardada = ordenTrabajoRepository.save(nuevaOrdenTrabajo);
	    
		
		return Optional.ofNullable(convertToDTO(ordenTrabajoGuardada));
		
	}

	
	//servicios.stream().map(servicio -> modelMapper.map(servicio, ServiciosDTO.class)).collect(Collectors.toList());
	
	@Override
	public OrdenTrabajoDTO updateOrdenTrabajo(Long id, OrdenTrabajoDTO ordenTrabajoActualizadaDTO) {
		// TODO Auto-generated method stub
		
		Optional<OrdenTrabajo> ordenTrabajoEncontrada = ordenTrabajoRepository.findById(id);
		Vehiculo vehiculoOrdenT = vehiculoRepository.findById(ordenTrabajoActualizadaDTO.getVehiculoId()).orElseThrow(() -> new NotFoundException("Vehiculo not found"));
	    List<Servicios> serviciosOrdenT = serviciosRepository.findAllById(ordenTrabajoActualizadaDTO.getServiciosId().stream().collect(Collectors.toList()));	    
	    Tecnico tecnicoOrdenT = tecnicoRepository.findById(ordenTrabajoActualizadaDTO.getTecnicoId()).orElseThrow(() -> new NotFoundException("Tecnico not found"));

	    if (ordenTrabajoActualizadaDTO == null || vehiculoOrdenT == null || tecnicoOrdenT == null || serviciosOrdenT.isEmpty()) {
			return null;
		}
	    
		//OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(id).orElse(null);
		if (ordenTrabajoEncontrada.isPresent()) {
			OrdenTrabajo ordenTrabajo = ordenTrabajoEncontrada.get();			
			ordenTrabajo.setFechaIngreso(ordenTrabajoActualizadaDTO.getFechaIngreso());
            ordenTrabajo.setFechaSalida(ordenTrabajoActualizadaDTO.getFechaSalida());
            ordenTrabajo.setVehiculo(vehiculoOrdenT);
            ordenTrabajo.setServicios(serviciosOrdenT);
            ordenTrabajo.setTecnico(tecnicoOrdenT);
            ordenTrabajo.setCostoTotal(ordenTrabajoActualizadaDTO.getCostoTotal());
            ordenTrabajo.setComentario(ordenTrabajoActualizadaDTO.getComentario());
            ordenTrabajo.setEstado(ordenTrabajoActualizadaDTO.getEstado());
            ordenTrabajoRepository.save(ordenTrabajo);
            
			return convertToDTO(ordenTrabajo);
		}
		return null;
	}

	@Override
	public void deleteOrdenTrabajo(Long id) {
		// TODO Auto-generated method stub
		ordenTrabajoRepository.deleteById(id);
	}

	public OrdenTrabajoDTO convertToDTO(OrdenTrabajo ordenTrabajo) {
        return OrdenTrabajoDTO.builder()
                .id(ordenTrabajo.getId())
                .vehiculoId(ordenTrabajo.getVehiculo().getId())
                .fechaIngreso(ordenTrabajo.getFechaIngreso())
                .fechaSalida(ordenTrabajo.getFechaSalida())
                .serviciosId(ordenTrabajo.getServicios().stream()
                        .map(Servicios::getId)
                        .collect(Collectors.toList()))
                .costoTotal(ordenTrabajo.getCostoTotal())
                .comentario(ordenTrabajo.getComentario())
                .estado(ordenTrabajo.getEstado())
                .tecnicoId(ordenTrabajo.getTecnico().getId())
                .build();
    }	

	private OrdenTrabajo convertToEntity(OrdenTrabajoDTO ordenTrabajoDTO) {
		OrdenTrabajo ordenTrabajo = OrdenTrabajo.builder()
				.vehiculo(Vehiculo.builder().id(ordenTrabajoDTO.getVehiculo().getId()).build())
				.fechaIngreso(ordenTrabajoDTO.getFechaIngreso()).fechaSalida(ordenTrabajoDTO.getFechaSalida())
				.servicios(ordenTrabajoDTO.getServicios().stream()
						.map(servicioDTO -> Servicios.builder().id(servicioDTO.getId()).build())
						.collect(Collectors.toList()))
				.costoTotal(ordenTrabajoDTO.getCostoTotal()).comentario(ordenTrabajoDTO.getComentario())
				.estado(ordenTrabajoDTO.getEstado())
				.tecnico(Tecnico.builder().id(ordenTrabajoDTO.getTecnico().getId()).build()).build();
		return ordenTrabajo;
	}

}
