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

import com.main.classDTO.ServiciosDTO;
import com.main.services.ServicioService;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {
		
	@Autowired
    private ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<ServiciosDTO>> getAllServicios() {
        List<ServiciosDTO> servicios = servicioService.getAllServicios();
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiciosDTO> getServicioById(@PathVariable Long id) {
        ServiciosDTO servicio = servicioService.getServicioById(id);
        return new ResponseEntity<>(servicio, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServiciosDTO> createServicio(@RequestBody ServiciosDTO servicioDTO) {
        ServiciosDTO createdServicio = servicioService.createServicio(servicioDTO);
        return new ResponseEntity<>(createdServicio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiciosDTO> updateServicio(@PathVariable Long id, @RequestBody ServiciosDTO servicioDTO) {
        ServiciosDTO updatedServicio = servicioService.updateServicio(id, servicioDTO);
        return new ResponseEntity<>(updatedServicio, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable Long id) {
        servicioService.deleteServicio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
