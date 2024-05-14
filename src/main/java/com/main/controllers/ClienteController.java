package com.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
