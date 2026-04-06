package com.tienda.bicicletas.service;

import com.tienda.bicicletas.model.Inventario;
import com.tienda.bicicletas.model.Venta;
import com.tienda.bicicletas.repository.InventarioRepository;
import com.tienda.bicicletas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Venta> ventasPorDia(LocalDate fecha) {
        return ventaRepository.findByFechaVentaBetween(fecha, fecha);
    }

    public List<Venta> ventasPorRango(LocalDate inicio, LocalDate fin) {
        return ventaRepository.findByFechaVentaBetween(inicio, fin);
    }

    public List<Inventario> stockActual() {
        return inventarioRepository.findAll();
    }
}