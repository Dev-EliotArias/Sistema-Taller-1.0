package com.main.classDTO;

import java.util.List;

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
    private DireccionDTO direccion;
    private String telefono;
    private TipoClienteDTO tipoCliente;
    private List<VehiculoDTO> vehiculo;
    private TipoPagoDTO tipoPago;
}