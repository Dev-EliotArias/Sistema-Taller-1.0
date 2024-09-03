package com.main.classDTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.main.entities.Especialidad;
import com.main.entities.TecEstado;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
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
	
	@NotBlank(message = "El nombre es obligatorio")
    private String nombre;
	
	@NotBlank(message = "El apellido es obligatorio")
    private String apellido;
	
	@NotBlank(message = "El DNI es obligatorio")
    private String dni;
	
	
    @JsonFormat(pattern="dd-MM-yyyy")
    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    private Date fechaNacimiento;
    
    @NotNull(message = "El sueldo es obligatorio")
    private Double sueldo;
    
    @Pattern(regexp = "\\d{3}\\d{3}\\d{3}", message = "El teléfono debe tener el formato correcto")
    private String telefono;
    
    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;
    
    @Email(message = "El email debe ser válido")
    private String correo;
    
    @NotNull(message = "La especialidad es obligatoria")
    @Enumerated(EnumType.STRING)    
    private Especialidad especialidad;
    
    @NotNull(message = "La fecha es obligatorio")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date fechaIngreso;
    
    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    private TecEstado estado;
}