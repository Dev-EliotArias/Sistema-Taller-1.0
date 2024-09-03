package com.main.controllers;

import java.util.List;
import java.util.Optional;

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

import com.main.classDTO.OrdenTrabajoDTO;
import com.main.services.OrdenTrabajoService;

@RestController
@RequestMapping("/api/ordenes-trabajo")
public class OrdenesTrabajoController {
	
	@Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @GetMapping
    public ResponseEntity<List<OrdenTrabajoDTO>> getAllOrdenesTrabajo() {
        List<OrdenTrabajoDTO> ordenesTrabajo= ordenTrabajoService.getAllOrdenesTrabajo();
        return new ResponseEntity<>(ordenesTrabajo, HttpStatus.OK);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<OrdenTrabajoDTO> getOrdenTrabajoById(@PathVariable Long id) {
        Optional<OrdenTrabajoDTO> optionalOrdenTrabajo = ordenTrabajoService.getOrdenTrabajoById(id);
        if (optionalOrdenTrabajo.isPresent()) {
            return new ResponseEntity<>(optionalOrdenTrabajo.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    

    @PostMapping
    public ResponseEntity<OrdenTrabajoDTO> createOrdenTrabajo(@RequestBody OrdenTrabajoDTO ordenTrabajoDTO) {
    	Optional<OrdenTrabajoDTO> optionalOrdenTrabajo = ordenTrabajoService.createOrdenTrabajo(ordenTrabajoDTO);
        return new ResponseEntity<>(optionalOrdenTrabajo.get(), HttpStatus.CREATED);
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<OrdenTrabajoDTO> updateOrdenTrabajo(@PathVariable Long id, @RequestBody OrdenTrabajoDTO ordenTrabajoDTO) {
        OrdenTrabajoDTO updatedOrdenTrabajo = ordenTrabajoService.updateOrdenTrabajo(id, ordenTrabajoDTO);
        if (updatedOrdenTrabajo != null) {
            return new ResponseEntity<>(updatedOrdenTrabajo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdenTrabajo(@PathVariable Long id) {
        ordenTrabajoService.deleteOrdenTrabajo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

}
