package com.tienda.bicicletas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "bicicletas")
@Data
public class Bicicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bicicleta")
    private int idBicicleta;

    private String codigo;
    private String marca;
    private String modelo;
    private String tipo;

    @Column(name = "precio_lista")
    private double precioLista;

    @JsonIgnore
    @OneToOne(mappedBy = "bicicleta", cascade = CascadeType.REMOVE)
    private Inventario inventario;

    @JsonIgnore
    @OneToMany(mappedBy = "bicicleta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detallesVenta;
}
