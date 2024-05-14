package com.main.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.classDTO.ClienteDTO;
import com.main.entities.Cliente;
import com.main.repositories.ClienteRepository;
import com.main.services.ClienteService;

@Service
public class ClienteImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
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
		Cliente cliente = clienteRepository.findById(id).orElseThrow();
		return modelMapper.map(cliente, ClienteDTO.class);
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
		Cliente cliente = clienteRepository.findById(id).orElseThrow();
		modelMapper.map(clienteDTO, cliente);
		cliente = clienteRepository.save(cliente);
		return modelMapper.map(cliente, ClienteDTO.class);
	}

	@Override
	public void deleteCliente(Long id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}

}
