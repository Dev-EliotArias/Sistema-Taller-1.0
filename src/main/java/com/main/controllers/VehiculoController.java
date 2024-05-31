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

import com.main.classDTO.VehiculoDTO;
import com.main.services.VehiculoService;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
	
	@Autowired
	private VehiculoService vehiculoService;
	
	@GetMapping
    public ResponseEntity<List<VehiculoDTO>> getAllVehiculos() {
        List<VehiculoDTO> vehiculos = vehiculoService.getAllVehiculos();
        return new ResponseEntity<>(vehiculos, HttpStatus.OK);
    }

	@GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> getVehiculoById(@PathVariable Long id) {
        VehiculoDTO vehiculo = vehiculoService.getVehiculoById(id);
        return new ResponseEntity<>(vehiculo, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<VehiculoDTO> createVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
        VehiculoDTO newVehiculo = vehiculoService.createVehiculo(vehiculoDTO);
        return new ResponseEntity<>(newVehiculo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoDTO> updateVehiculo(@PathVariable Long id, @RequestBody VehiculoDTO vehiculoDTO) {
        VehiculoDTO updatedVehiculo = vehiculoService.updateVehiculo(id, vehiculoDTO);
        return new ResponseEntity<>(updatedVehiculo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable Long id) {
        vehiculoService.deleteVehiculo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    

}
