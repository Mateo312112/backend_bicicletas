package com.tienda.bicicletas.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bicicleta")
    @JsonIgnoreProperties({"inventario", "detallesVenta"})
    private Bicicleta bicicleta;

    @Column(name = "cantidad_disponible")
    private int cantidadDisponible;

    @Column(name = "stock_minimo")
    private int stockMinimo;

    @Column(name = "ultima_actualizacion")
    private LocalDate ultimaActualizacion;

}