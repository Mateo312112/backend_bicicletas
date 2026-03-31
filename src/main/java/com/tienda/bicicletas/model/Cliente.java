package com.tienda.bicicletas.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;


@Entity
@Table(name = "clientes")
@Data
public class Cliente {
    @Id
    private int documento;

    private String nombre;

    private String telefono;

}