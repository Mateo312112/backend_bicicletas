package com.tienda.bicicletas.model;

import jakarta.persistence.*;
import lombok.Data;
import com.tienda.bicicletas.model.Bicicleta;
import com.tienda.bicicletas.model.Venta;

@Entity
@Table(name = "detalles_ventas")
@Data
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private int idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_bicicleta")
    private Bicicleta bicicleta;

    private int cantidad;

    @Column(name = "precio_unitario_venta")
    private double precioUnitarioVenta;

    private double subtotal;
}