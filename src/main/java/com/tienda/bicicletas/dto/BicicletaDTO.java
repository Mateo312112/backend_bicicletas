package com.tienda.bicicletas.dto;

import lombok.Data;

@Data
public class BicicletaDTO {

    private String codigo;
    private String marca;
    private String modelo;
    private String tipo;
    private double precioLista;
}