package com.main.entities;

import java.util.List;

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
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombres;
	private String apellidos;
	private String dni;
	private String ruc;
	private String razonSocial;
	private String correo;
	private String direccion;
	private String telefono;
	
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;
	
	@OneToMany(mappedBy = "cliente")
	private List<Vehiculo> vehiculo;
	
	
	
}
