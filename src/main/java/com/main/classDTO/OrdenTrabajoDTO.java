package com.main.classDTO;

import java.util.Date;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.main.entities.Estado;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class OrdenTrabajoDTO {
	
    private Long id;
    @NotBlank(message = "El vehiculo es obligatorio")
    private Long vehiculoId;
    
    @NotNull(message = "La fecha es obligatorio")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date fechaIngreso;
    
    @NotNull(message = "La fecha es obligatorio")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date fechaSalida;
    
    @NotNull(message = "Debe agregar mas de un Servicio")
    private List<Long> serviciosId;
    
    @NotBlank(message = "El costo es obligatorio")
    private Double costoTotal;
    
    private String comentario;
    
    @NotBlank(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    private Estado estado;
    
    @NotBlank(message = "Debe de asignar un tecnico al trabajo")
    private Long tecnicoId;
}
