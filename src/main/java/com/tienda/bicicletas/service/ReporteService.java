package com.tienda.bicicletas.service;

import com.tienda.bicicletas.model.Inventario;
import com.tienda.bicicletas.model.Venta;
import com.tienda.bicicletas.repository.InventarioRepository;
import com.tienda.bicicletas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    // Todas las ventas agrupadas (el frontend las agrupa por fecha)
    public List<Venta> ventasPorDia() {
        return ventaRepository.findAll();
    }

    // Ventas en un rango de fechas
    public List<Venta> ventasPorRango(LocalDate inicio, LocalDate fin) {
        return ventaRepository.findByFechaVentaBetween(inicio, fin);
    }

    // Stock actual completo
    public List<Inventario> stockActual() {
        return inventarioRepository.findAll();
    }

    // Solo productos bajo stock mínimo
    public List<Inventario> stockCritico() {
        return inventarioRepository.findAll().stream()
                .filter(i -> i.getCantidadDisponible() <= i.getStockMinimo())
                .collect(Collectors.toList());
    }
}