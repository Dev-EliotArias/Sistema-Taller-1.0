package com.main.classDTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturaDTO {
    private Long id;
    private VehiculoDTO vehiculo;
    private ClienteDTO cliente;
    private OrdenTrabajoDTO ordenTrabajo;
    private Date fecha;
}