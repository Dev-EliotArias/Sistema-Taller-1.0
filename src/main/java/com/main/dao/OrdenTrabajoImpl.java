package com.main.dao;

import java.util.List;
import java.util.stream.Collectors;

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
import com.main.repositories.OrdenTrabajoRepository;
import com.main.services.OrdenTrabajoService;

@Service
public class OrdenTrabajoImpl implements OrdenTrabajoService {

	@Autowired
	private OrdenTrabajoRepository ordenTrabajoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<OrdenTrabajoDTO> getAllOrdenesTrabajo() {
		// TODO Auto-generated method stub
		List<OrdenTrabajo> ordenesTrabajo = ordenTrabajoRepository.findAll();
		return ordenesTrabajo.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public OrdenTrabajoDTO getOrdenTrabajoById(Long id) {
		// TODO Auto-generated method stub
		OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(id).orElse(null);
		return ordenTrabajo != null ? convertToDTO(ordenTrabajo) : null;
	}

	@Override
	public OrdenTrabajoDTO createOrdenTrabajo(OrdenTrabajoDTO ordenTrabajoDTO) {
		// TODO Auto-generated method stub
		OrdenTrabajo ordenTrabajo = convertToEntity(ordenTrabajoDTO);
		OrdenTrabajo newOrdenTrabajo = ordenTrabajoRepository.save(ordenTrabajo);
		return convertToDTO(newOrdenTrabajo);
	}

	
	//servicios.stream().map(servicio -> modelMapper.map(servicio, ServiciosDTO.class)).collect(Collectors.toList());
	
	@Override
	public OrdenTrabajoDTO updateOrdenTrabajo(Long id, OrdenTrabajoDTO ordenTrabajoDTO) {
		// TODO Auto-generated method stub
		OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(id).orElse(null);
		if (ordenTrabajo != null) {
			ordenTrabajo.setFechaIngreso(ordenTrabajoDTO.getFechaIngreso());
            ordenTrabajo.setFechaSalida(ordenTrabajoDTO.getFechaSalida());
            ordenTrabajo.setServicios(ordenTrabajoDTO.getServicios().stream().map(servicioDTO -> modelMapper.map(servicioDTO, Servicios.class)).collect(Collectors.toList()));
            ordenTrabajo.setTecnico(modelMapper.map(ordenTrabajoDTO.getTecnico(), Tecnico.class));
            ordenTrabajo.setCostoTotal(ordenTrabajoDTO.getCostoTotal());
            ordenTrabajo.setComentario(ordenTrabajoDTO.getComentario());
            ordenTrabajo.setEstado(ordenTrabajoDTO.getEstado());
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
						.map(servicio -> ServiciosDTO.builder().id(servicio.getId()).nombre(servicio.getNombre())
								.costo(servicio.getCosto()).descripcion(servicio.getDescripcion())
								.tiempoEstimado(servicio.getTiempoEstimado().doubleValue()).build())
						.collect(Collectors.toList()))
				.costoTotal(ordenTrabajo.getCostoTotal()).comentario(ordenTrabajo.getComentario())
				.estado(ordenTrabajo.getEstado())
				.tecnico(TecnicoDTO.builder().id(ordenTrabajo.getTecnico().getId())
						.nombre(ordenTrabajo.getTecnico().getNombre()).apellido(ordenTrabajo.getTecnico().getApellido())
						.dni(ordenTrabajo.getTecnico().getDni())
						.fechaNacimiento(ordenTrabajo.getTecnico().getFechaNacimiento())
						.sueldo(ordenTrabajo.getTecnico().getSueldo()).telefono(ordenTrabajo.getTecnico().getTelefono())
						.direccion(ordenTrabajo.getTecnico().getDireccion()).build())
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
