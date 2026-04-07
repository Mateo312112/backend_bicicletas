package com.tienda.bicicletas.repository;

import com.tienda.bicicletas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Integer>
{
    List<Venta> findByClienteDocumento(String documento);

    List<Venta> findByFechaVentaBetween(LocalDate inicio, LocalDate fin);

    @Query("SELECT d.bicicleta.idBicicleta, d.bicicleta.marca, d.bicicleta.modelo, SUM(d.cantidad) as total " +
            "FROM DetalleVenta d " +
            "GROUP BY d.bicicleta.idBicicleta, d.bicicleta.marca, d.bicicleta.modelo " +
            "ORDER BY total DESC")
    List<Object[]> findTopProductos();

}
