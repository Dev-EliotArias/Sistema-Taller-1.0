package com.main.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.classDTO.ClienteDTO;
import com.main.classDTO.VehiculoDTO;
import com.main.entities.Cliente;
import com.main.entities.Vehiculo;
import com.main.exceptions.ClienteNotFoundException;
import com.main.repositories.ClienteRepository;
import com.main.repositories.VehiculoRepository;
import com.main.services.VehiculoService;

@Service
public class VehiculoImpl implements VehiculoService {

	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<VehiculoDTO> getAllVehiculos() {
		// TODO Auto-generated method stub
		List<Vehiculo> vehiculos = vehiculoRepository.findAll();		
		return vehiculos.stream()
		        .map(vehiculo -> {
		            VehiculoDTO vehiculoDTO = modelMapper.map(vehiculo, VehiculoDTO.class);
		            vehiculoDTO.setPropietarioId(vehiculo.getCliente().getId());
		            return vehiculoDTO;
		        })
		        .collect(Collectors.toList());
	}

	/*
	 * @Override public VehiculoDTO getVehiculoById(Long id) throws
	 * ClienteNotFoundException{ // TODO Auto-generated method stub Vehiculo
	 * vehiculo = vehiculoRepository.findById(id).orElse(null);
	 * 
	 * if (vehiculo != null) { VehiculoDTO vehiculoDTO = VehiculoDTO.builder()
	 * .id(vehiculo.getId()) .marca(vehiculo.getMarca())
	 * .modelo(vehiculo.getModelo()) .color(vehiculo.getColor())
	 * .anio(vehiculo.getAnio()) .placa(vehiculo.getPlaca())
	 * .propietarioId(vehiculo.getId()) .build();
	 * 
	 * 
	 * return vehiculoDTO; } else { throw new
	 * ClienteNotFoundException("Cliente not found for the given vehiculo id"); } }
	 */
	
	@Override
    public VehiculoDTO getVehiculoById(Long id) {
        Vehiculo vehiculoFromDB = vehiculoRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Vehiculo not found"));
        VehiculoDTO vehiculoDTO = modelMapper.map(vehiculoFromDB, VehiculoDTO.class);
        vehiculoDTO.setPropietarioId(vehiculoFromDB.getCliente().getId());
        return vehiculoDTO;
    }
	
	/*
	 * @Override public VehiculoDTO createVehiculo(Long idCliente, VehiculoDTO
	 * vehiculoDTO) { Cliente cliente =
	 * clienteRepository.findById(idCliente).orElseThrow(() -> new
	 * ClienteNotFoundException("Cliente not found"));
	 * 
	 * //System.out.println(cliente.getNombreRazonSocial());
	 * 
	 * vehiculoDTO.setPropietarioId(cliente.getId());
	 * 
	 * 
	 * Vehiculo vehiculo = Vehiculo.builder() .marca(vehiculoDTO.getMarca())
	 * .modelo(vehiculoDTO.getModelo()) .color(vehiculoDTO.getColor())
	 * .anio(vehiculoDTO.getAnio()) .placa(vehiculoDTO.getPlaca()) .cliente(cliente)
	 * .build();
	 * 
	 * 
	 * vehiculo = vehiculoRepository.save(vehiculo); return
	 * modelMapper.map(vehiculo, VehiculoDTO.class); }
	 */
	public VehiculoDTO createVehiculo(VehiculoDTO vehiculoDTO) {
        Cliente cliente = clienteRepository.findById(vehiculoDTO.getPropietarioId()).orElseThrow(() -> new ClienteNotFoundException("Cliente not found"));
        Vehiculo vehiculo = modelMapper.map(vehiculoDTO, Vehiculo.class);
        vehiculo.setCliente(cliente);
        Vehiculo savedVehiculo = vehiculoRepository.save(vehiculo);
        VehiculoDTO vehiculoDTOSaved = modelMapper.map(savedVehiculo, VehiculoDTO.class);
        vehiculoDTOSaved.setPropietarioId(savedVehiculo.getCliente().getId());
        return vehiculoDTOSaved;
    }
	
	public VehiculoDTO updateVehiculo(Long idVehiculo, VehiculoDTO vehiculoDTO) {
	    Vehiculo vehiculoToUpdate = vehiculoRepository.findById(idVehiculo).orElseThrow(() -> new ClienteNotFoundException("Vehiculo not found"));
	    if(vehiculoToUpdate != null) {
	    	
	    	Cliente cliente = clienteRepository.findById(vehiculoDTO.getPropietarioId()).orElseThrow(() -> new ClienteNotFoundException("Cliente not found"));
	    	if(cliente != null) {
	    		// Carga el objeto desde el repositorio utilizando el método findById
	    		Vehiculo existingVehiculo = vehiculoRepository.findById(idVehiculo).orElseThrow(() -> new ClienteNotFoundException("Vehiculo not found"));
	    		// Establece los nuevos valores en el objeto cargado
	    		existingVehiculo.setMarca(vehiculoDTO.getMarca());
	    		existingVehiculo.setModelo(vehiculoDTO.getModelo());
	    		existingVehiculo.setColor(vehiculoDTO.getColor());
	    		existingVehiculo.setAnio(vehiculoDTO.getAnio());
	    		existingVehiculo.setPlaca(vehiculoDTO.getPlaca());
	    		existingVehiculo.setCliente(cliente);
	    		
	    		Vehiculo updatedVehiculo = vehiculoRepository.save(existingVehiculo);
	    		VehiculoDTO vehiculoDTOUpdated = modelMapper.map(updatedVehiculo, VehiculoDTO.class);
	    		vehiculoDTOUpdated.setPropietarioId(updatedVehiculo.getCliente().getId());
	    		return vehiculoDTOUpdated;
	    		
	    	}else {
	    		System.err.println("Cliente no encontrado");
	    	}
	    	
	    		
	    	
	    }else {
	    	System.err.println("Vehiculo no encontrado");
	    }
	    return null;
	    
	}
	
	/*
	 * @Override public VehiculoDTO updateVehiculo(Long idVehiculo, VehiculoDTO
	 * vehiculoDTO) { // TODO Auto-generated method stub Vehiculo vehiculoToUpdate =
	 * vehiculoRepository.findById(idVehiculo).orElseThrow();
	 * 
	 * if (vehiculoToUpdate != null) {
	 * vehiculoToUpdate.setMarca(vehiculoDTO.getMarca());
	 * vehiculoToUpdate.setModelo(vehiculoDTO.getModelo());
	 * vehiculoToUpdate.setColor(vehiculoDTO.getColor());
	 * vehiculoToUpdate.setAnio(vehiculoDTO.getAnio());
	 * vehiculoToUpdate.setPlaca(vehiculoDTO.getPlaca());
	 * 
	 * Optional<Cliente> cliente =
	 * clienteRepository.findById(vehiculoDTO.getPropietarioId());
	 * 
	 * if (cliente.isPresent()) { vehiculoToUpdate.setCliente(cliente.get());
	 * 
	 * 
	 * }
	 * 
	 * 
	 * Vehiculo vehiculoUpdated = vehiculoRepository.save(vehiculoToUpdate); return
	 * modelMapper.map(vehiculoUpdated, VehiculoDTO.class); } return null; }
	 */

	@Override
	public void deleteVehiculo(Long id) {
		// TODO Auto-generated method stub
		System.out.println("Asdsdasdasdasd");
		vehiculoRepository.deleteById(id);
	}

}
