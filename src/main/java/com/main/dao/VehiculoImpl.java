package com.main.dao;

import java.util.List;
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
		return vehiculos.stream().map(vehiculo -> modelMapper.map(vehiculo, VehiculoDTO.class)).collect(Collectors.toList());
	}

	@Override
	public VehiculoDTO getVehiculoById(Long id) throws ClienteNotFoundException{
		// TODO Auto-generated method stub
		Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);
        if (vehiculo != null) {
            return modelMapper.map(vehiculo, VehiculoDTO.class);
        } else {
            throw new ClienteNotFoundException("Cliente not found for the given vehiculo id");
        }
	}
	
	@Override
	public VehiculoDTO createVehiculo(Long clienteId, VehiculoDTO vehiculoDTO) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNotFoundException("Cliente not found"));
        vehiculoDTO.setCliente(modelMapper.map(cliente, ClienteDTO.class));
        Vehiculo vehiculo = modelMapper.map(vehiculoDTO, Vehiculo.class);
        vehiculo = vehiculoRepository.save(vehiculo);
        return modelMapper.map(vehiculo, VehiculoDTO.class);
    }

//	@Override
//	public VehiculoDTO createVehiculo(VehiculoDTO vehiculoDTO) {
//		// TODO Auto-generated method stub
//		Vehiculo vehiculo = vehiculoRepository.save(modelMapper.map(vehiculoDTO, Vehiculo.class));
//      return modelMapper.map(vehiculo, VehiculoDTO.class);
//  }

	@Override
	public VehiculoDTO updateVehiculo(Long id, VehiculoDTO vehiculoDTO) {
		// TODO Auto-generated method stub
		Vehiculo vehiculo = vehiculoRepository.findById(id).orElseThrow();
        if (vehiculo != null) {
            vehiculo.setMarca(vehiculoDTO.getMarca());
            vehiculo.setModelo(vehiculoDTO.getModelo());
            vehiculo.setColor(vehiculoDTO.getColor());
            vehiculo.setAnio(vehiculoDTO.getAnio());
            vehiculo.setPlaca(vehiculoDTO.getPlaca());
            
            Long newClienteId = vehiculoDTO.getCliente() != null ? vehiculoDTO.getCliente().getId() : null;
            if (newClienteId != null && !newClienteId.equals(vehiculo.getCliente().getId())) {
				Cliente nuevoCliente = clienteRepository.findById(newClienteId).orElseThrow(() -> new ClienteNotFoundException("Cliente not found"));
	            vehiculo.setCliente(nuevoCliente);
			}
            
            
            vehiculo = vehiculoRepository.save(vehiculo);
            return modelMapper.map(vehiculo, VehiculoDTO.class);
        }
        return null;
	}

	@Override
	public void deleteVehiculo(Long id) {
		// TODO Auto-generated method stub
		vehiculoRepository.deleteById(id);
	}

}
