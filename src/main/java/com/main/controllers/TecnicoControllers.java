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

import com.main.classDTO.TecnicoDTO;
import com.main.services.TecnicoService;

@RestController
@RequestMapping("/api/tecnicos")
public class TecnicoControllers {
	
	@Autowired
    private TecnicoService tecnicoService;

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> getAllTecnicos() {
        List<TecnicoDTO> tecnicos = tecnicoService.getAllTecnicos();
        return new ResponseEntity<>(tecnicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> getTecnicoById(@PathVariable Long id) {
        TecnicoDTO tecnico = tecnicoService.getTecnicoById(id);
        return new ResponseEntity<>(tecnico, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> createTecnico(@RequestBody TecnicoDTO tecnicoDTO) {
        TecnicoDTO createdTecnico = tecnicoService.createTecnico(tecnicoDTO);
        return new ResponseEntity<>(createdTecnico, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> updateTecnico(@PathVariable Long id, @RequestBody TecnicoDTO tecnicoDTO) {
        TecnicoDTO updatedTecnico = tecnicoService.updateTecnico(id, tecnicoDTO);
        return new ResponseEntity<>(updatedTecnico, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTecnico(@PathVariable Long id) {
        tecnicoService.deleteTecnico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
