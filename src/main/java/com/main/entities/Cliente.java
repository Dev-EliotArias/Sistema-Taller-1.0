package com.main.entities;
import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private String nombreRazonSocial;
    
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    
    private String numeroDoc;
    private String correo;
    
    private String direccion;   
    
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaRegistro;
    private String telefono;
    
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonIgnoreProperties("cliente")
    private List<Vehiculo> vehiculo;
    @Enumerated(EnumType.STRING)
    private TipoPago tipoPago;
	
	
	
}
