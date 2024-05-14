package com.main.classDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculoDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String color;
    private String anio;
    private String placa;
    private ClienteDTO cliente;
}