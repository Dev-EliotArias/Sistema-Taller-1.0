package com.main.classDTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TecnicoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date fechaNacimiento;
    private Double sueldo;
    private String telefono;
    private String direccion;
}