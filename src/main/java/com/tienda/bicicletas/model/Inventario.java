package com.tienda.bicicletas.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table (name = "inventario")
@Data

public class Inventario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private int idInventario;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_bicicleta")
    private Bicicleta bicicleta;

    @Column(name = "cantidad_disponible")
    private int cantidadDisponible;

    @Column(name = "stock_minimo")
    private int stockMinimo;

    @Column(name = "ultima_actualizacion")
    private LocalDate ultimaActualizacion;

}