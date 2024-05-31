package com.main.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.classDTO.TecnicoDTO;
import com.main.entities.Tecnico;
import com.main.repositories.TecnicoRepository;
import com.main.services.TecnicoService;

@Service
public class TecnicoImpl implements TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
    public List<TecnicoDTO> getAllTecnicos() {
        List<Tecnico> tecnicos = tecnicoRepository.findAll();
        return tecnicos.stream().map(tecnico -> modelMapper.map(tecnico, TecnicoDTO.class)).collect(Collectors.toList());
    }

    @Override
    public TecnicoDTO getTecnicoById(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tecnico no Econtrado"));
        return modelMapper.map(tecnico, TecnicoDTO.class);
    }

    @Override
    public TecnicoDTO createTecnico(TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = modelMapper.map(tecnicoDTO, Tecnico.class);
        tecnico = tecnicoRepository.save(tecnico);
        return modelMapper.map(tecnico, TecnicoDTO.class);
    }

    @Override
    public TecnicoDTO updateTecnico(Long id, TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = tecnicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tecnico not found"));
        tecnico.setNombre(tecnicoDTO.getNombre());
        tecnico.setApellido(tecnicoDTO.getApellido());
        tecnico.setDni(tecnicoDTO.getDni());
        tecnico.setFechaNacimiento(tecnicoDTO.getFechaNacimiento());
        tecnico.setSueldo(tecnicoDTO.getSueldo());
        tecnico.setTelefono(tecnicoDTO.getTelefono());
        tecnico.setDireccion(tecnicoDTO.getDireccion());
        tecnico.setCorreo(tecnicoDTO.getCorreo());
        tecnico.setEspecialidad(tecnicoDTO.getEspecialidad());
        tecnico.setFechaIngreso(tecnicoDTO.getFechaIngreso());
        tecnico.setEstado(tecnicoDTO.getEstado());
        tecnico = tecnicoRepository.save(tecnico);
        return modelMapper.map(tecnico, TecnicoDTO.class);
    }

    @Override
    public void deleteTecnico(Long id) {
        tecnicoRepository.deleteById(id);
    }

}
