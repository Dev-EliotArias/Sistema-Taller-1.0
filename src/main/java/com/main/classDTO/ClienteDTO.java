package com.main.classDTO;

import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.main.entities.TipoCliente;
import com.main.entities.TipoDocumento;
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
    private String nombreRazonSocial;
    private TipoDocumento tipoDocumento;
    private String numeroDoc;
    private String correo;
    private String direccion;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaRegistro;
    private String telefono;
    private TipoCliente tipoCliente;
    private List<VehiculoDTO> vehiculos;
    private TipoPago tipoPago;
}