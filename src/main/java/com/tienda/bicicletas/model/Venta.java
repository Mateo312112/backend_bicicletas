package com.tienda.bicicletas.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ventas")
@Data

public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private int idVenta;

    @ManyToOne
    @JoinColumn(name = "documento_cliente")

    private Cliente cliente;
    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;
    @Column(name = "total_venta")
    private BigDecimal totalVenta;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detallesVenta;

}
