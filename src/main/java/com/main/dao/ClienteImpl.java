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
import com.main.repositories.ClienteRepository;
import com.main.repositories.VehiculoRepository;
import com.main.services.ClienteService;

@Service
public class ClienteImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ClienteDTO> getAllClients() {
		// TODO Auto-generated method stub
		List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());		
	}
	
	@Override
	public ClienteDTO getClientById(Long id) {
		// TODO Auto-generated method stub
		Cliente cliente = clienteRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Cliente no encontrado"));
		ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
		List<Vehiculo> vehiculos = vehiculoRepository.findByClienteId(id);
		List<VehiculoDTO> vehiculosDTO = vehiculos.stream()
				.map(vehiculo -> modelMapper.map(vehiculo, VehiculoDTO.class))
				.collect(Collectors.toList());
		clienteDTO.setVehiculos(vehiculosDTO);		
		return clienteDTO;
	}

	@Override
	public ClienteDTO createClient(ClienteDTO clienteDTO) {
		// TODO Auto-generated method stub
		Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        cliente = clienteRepository.save(cliente);
        return modelMapper.map(cliente, ClienteDTO.class);
	}

	@Override
	public ClienteDTO updateClient(Long id, ClienteDTO clienteDTO) {
		// TODO Auto-generated method stub		
		Cliente clienteFromDB = clienteRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Cliente no encontrado"));		
		clienteFromDB.setNombre(clienteDTO.getNombre());
	    clienteFromDB.setApellido(clienteDTO.getApellido());
	    clienteFromDB.setDni(clienteDTO.getDni());
	    clienteFromDB.setRuc(clienteDTO.getRuc());
	    clienteFromDB.setRazonSocial(clienteDTO.getRazonSocial());
	    clienteFromDB.setCorreo(clienteDTO.getCorreo());
	    clienteFromDB.setDireccion(clienteDTO.getDireccion());
	    clienteFromDB.setTelefono(clienteDTO.getTelefono());
	    clienteFromDB.setTipoCliente(clienteDTO.getTipoCliente());
	    clienteFromDB.setTipoPago(clienteDTO.getTipoPago());		
		Cliente updatedCliente = clienteRepository.save(clienteFromDB);
		return modelMapper.map(updatedCliente, ClienteDTO.class);
	}	

	@Override
	public void deleteCliente(Long id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}

}
