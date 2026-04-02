package com.tienda.bicicletas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bicicletas")
@Data
public class Bicicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbicicleta")
    private int idbicicleta;

    private String codigo;
    private String marca;
    private String modelo;
    private String tipo;

    @Column(name = "precio_lista")
    private double precioLista;

}