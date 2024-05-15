package com.main.entities;

import java.util.Date;
import java.util.List;

import com.main.classDTO.ServiciosDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
    private Date fechaIngreso;
    private Date fechaSalida;
    @ManyToMany
    @JoinTable(name = "orden_trabajo_servicios",
            joinColumns = @JoinColumn(name = "orden_trabajo_id"),
            inverseJoinColumns = @JoinColumn(name = "servicios_id"))
    private List<Servicios> servicios;
    private Double costoTotal;
    private String comentario;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;
}
