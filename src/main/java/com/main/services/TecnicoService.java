package com.main.services;

import java.util.List;

import com.main.classDTO.TecnicoDTO;

public interface TecnicoService {
	
	List<TecnicoDTO> getAllTecnicos();
    TecnicoDTO getTecnicoById(Long id);
    TecnicoDTO createTecnico(TecnicoDTO tecnicoDTO);
    TecnicoDTO updateTecnico(Long id, TecnicoDTO tecnicoDTO);
    void deleteTecnico(Long id);

}
