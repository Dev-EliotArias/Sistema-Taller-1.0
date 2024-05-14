package com.main.services;

import java.util.List;

import com.main.classDTO.ClienteDTO;

public interface ClienteService {
	
	public List<ClienteDTO> getAllClients();
	
	public ClienteDTO getClientById(Long id);
	
	public ClienteDTO createClient(ClienteDTO clienteDTO);
	
	public ClienteDTO updateClient(Long id, ClienteDTO clienteDTO);
	
	public void deleteCliente(Long id);
}
