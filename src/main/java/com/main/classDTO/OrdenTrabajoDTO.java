package com.main.classDTO;

import java.util.Date;
import java.util.List;

import com.main.entities.Estado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenTrabajoDTO {
    private Long id;
    private VehiculoDTO vehiculo;
    private Date fechaIngreso;
    private Date fechaSalida;
    private List<ServiciosDTO> servicios;
    private Double costoTotal;
    private String comentario;
    private Estado estado;
    private TecnicoDTO tecnico;
}
