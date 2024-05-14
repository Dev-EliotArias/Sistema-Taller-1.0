package com.main.classDTO;

import java.util.List;

import com.main.entities.TipoCliente;
import com.main.entities.TipoPago;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String ruc;
    private String razonSocial;
    private String correo;
    private String direccion;
    private String telefono;
    private TipoCliente tipoCliente;
    private List<VehiculoDTO> vehiculo;
    private TipoPago tipoPago;
}