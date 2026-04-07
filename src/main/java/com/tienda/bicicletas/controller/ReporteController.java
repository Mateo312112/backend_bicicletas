package com.tienda.bicicletas.controller;

import com.tienda.bicicletas.model.Inventario;
import com.tienda.bicicletas.model.Venta;
import com.tienda.bicicletas.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/ventas-por-dia")
    public List<Venta> ventasPorDia() {
        return reporteService.ventasPorDia();
    }

    @GetMapping("/ventas-por-dia/rango")
    public List<Venta> ventasPorRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return reporteService.ventasPorRango(inicio, fin);
    }

    @GetMapping("/top-productos")
    public List<Venta> topProductos() {
        return reporteService.ventasPorDia(); // mismas ventas, el frontend extrae los productos
    }

    @GetMapping("/stock")
    public List<Inventario> stockActual() {
        return reporteService.stockActual();
    }

    @GetMapping("/stock/critico")
    public List<Inventario> stockCritico() {
        return reporteService.stockCritico();
    }
}