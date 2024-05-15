package com.main.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return ordenesTrabajo.stream().map(this::convertToDTO).collect(Collectors.toList());
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
		
		if (nuevaOrdenTrabajoDTO == null || nuevaOrdenTrabajoDTO.getVehiculo() == null || nuevaOrdenTrabajoDTO.getTecnico() == null || nuevaOrdenTrabajoDTO.getServicios() == null) {
			return null;
		}
		
		Vehiculo vehiculo = vehiculoRepository.findById(nuevaOrdenTrabajoDTO.getVehiculo().getId()).orElseThrow(() -> new NotFoundException("Vehiculo not found"));
	    List<Servicios> servicios = serviciosRepository.findAllById(nuevaOrdenTrabajoDTO.getServicios().stream().map(ServiciosDTO::getId).collect(Collectors.toList()));
	    Tecnico tecnico = tecnicoRepository.findById(nuevaOrdenTrabajoDTO.getTecnico().getId()).orElseThrow(() -> new NotFoundException("Tecnico not found"));
	    
	    
	    //OrdenTrabajo existingOrder = ordenTrabajoRepository.findByVehiculoAndServiciosAndTecnico(vehiculo, servicios, tecnico);
	    //if (existingOrder!= null) {
	    //    throw new ConflictException("OrdenTrabajo already exists");
	    //}
	    
	    OrdenTrabajo nuevaOrdenTrabajo = OrdenTrabajo.builder()
	            .vehiculo(vehiculo)
	            .servicios(servicios)
	            .tecnico(tecnico)
	            .fechaIngreso(nuevaOrdenTrabajoDTO.getFechaIngreso())
	            .fechaSalida(nuevaOrdenTrabajoDTO.getFechaSalida())
	            .costoTotal(nuevaOrdenTrabajoDTO.getCostoTotal())
	            .comentario(nuevaOrdenTrabajoDTO.getComentario())
	            .estado(nuevaOrdenTrabajoDTO.getEstado())
	            .build();
	    
	    OrdenTrabajo ordenTrabajoGuardada = ordenTrabajoRepository.save(nuevaOrdenTrabajo);
		
		return Optional.ofNullable(modelMapper.map(ordenTrabajoGuardada, OrdenTrabajoDTO.class));
		
	}

	
	//servicios.stream().map(servicio -> modelMapper.map(servicio, ServiciosDTO.class)).collect(Collectors.toList());
	
	@Override
	public OrdenTrabajoDTO updateOrdenTrabajo(Long id, OrdenTrabajoDTO ordenTrabajoActualizadaDTO) {
		// TODO Auto-generated method stub
		Optional<OrdenTrabajo> ordenTrabajoEncontrada = ordenTrabajoRepository.findById(id);
		//OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(id).orElse(null);
		if (ordenTrabajoEncontrada.isPresent()) {
			OrdenTrabajo ordenTrabajo = ordenTrabajoEncontrada.get();			
			ordenTrabajo.setFechaIngreso(ordenTrabajoActualizadaDTO.getFechaIngreso());
            ordenTrabajo.setFechaSalida(ordenTrabajoActualizadaDTO.getFechaSalida());
            ordenTrabajo.setServicios(ordenTrabajoActualizadaDTO.getServicios().stream().map(servicioDTO -> modelMapper.map(servicioDTO, Servicios.class)).collect(Collectors.toList()));
            ordenTrabajo.setTecnico(modelMapper.map(ordenTrabajoActualizadaDTO.getTecnico(), Tecnico.class));
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

	private OrdenTrabajoDTO convertToDTO(OrdenTrabajo ordenTrabajo) {
		OrdenTrabajoDTO ordenTrabajoDTO = OrdenTrabajoDTO.builder().
				id(ordenTrabajo.getId())
				.vehiculo(VehiculoDTO.builder().id(ordenTrabajo.getVehiculo().getId())
						.marca(ordenTrabajo.getVehiculo().getMarca())
						.modelo(ordenTrabajo.getVehiculo().getModelo())
						.color(ordenTrabajo.getVehiculo().getColor())
						.anio(ordenTrabajo.getVehiculo().getAnio())
						.placa(ordenTrabajo.getVehiculo().getPlaca())
						.build())
				.fechaIngreso(ordenTrabajo.getFechaIngreso()).fechaSalida(ordenTrabajo.getFechaSalida())
				.servicios(ordenTrabajo.getServicios().stream()
						.map(servicio -> ServiciosDTO.builder()
								.id(servicio.getId())
								.nombre(servicio.getNombre())
								.costo(servicio.getCosto())
								.descripcion(servicio.getDescripcion())
								.tiempoEstimado(servicio.getTiempoEstimado())
								.build())
						.collect(Collectors.toList()))
				.costoTotal(ordenTrabajo.getCostoTotal()).comentario(ordenTrabajo.getComentario())
				.estado(ordenTrabajo.getEstado())
				.tecnico(TecnicoDTO.builder()
						.id(ordenTrabajo.getTecnico().getId())
						.nombre(ordenTrabajo.getTecnico().getNombre())
						.apellido(ordenTrabajo.getTecnico().getApellido())
						.dni(ordenTrabajo.getTecnico().getDni())
						.fechaNacimiento(ordenTrabajo.getTecnico().getFechaNacimiento())
						.sueldo(ordenTrabajo.getTecnico().getSueldo())
						.telefono(ordenTrabajo.getTecnico().getTelefono())
						.direccion(ordenTrabajo.getTecnico().getDireccion())
						.build())
				.build();
		return ordenTrabajoDTO;
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
