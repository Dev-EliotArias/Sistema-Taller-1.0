package com.main.entities;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdenTrabajo {
	
	private Long id;
	private Vehiculo vehiculo;	
	
	
	
	
}
