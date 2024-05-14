package com.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.classDTO.ClienteDTO;
import com.main.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> getAllClientes() {

		List<ClienteDTO> clienteDTOs = clienteService.getAllClients();
		return new ResponseEntity<>(clienteDTOs, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
		ClienteDTO clienteDTO = clienteService.getClientById(id);
		return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
		ClienteDTO createdClienteDTO = clienteService.createClient(clienteDTO);
		return new ResponseEntity<>(createdClienteDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {		
		ClienteDTO updatedClienteDTO = clienteService.updateClient(id, clienteDTO);
		return new ResponseEntity<>(updatedClienteDTO, HttpStatus.OK);
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
