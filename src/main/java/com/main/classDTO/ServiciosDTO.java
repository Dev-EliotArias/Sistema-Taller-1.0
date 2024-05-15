package com.main.classDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiciosDTO {
    private Long id;
    private String nombre;
    private Double costo;
    private String descripcion;
    private Integer tiempoEstimado;
}