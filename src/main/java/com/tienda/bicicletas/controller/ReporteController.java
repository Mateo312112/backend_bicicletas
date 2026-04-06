package com.tienda.bicicletas.controller;

import com.tienda.bicicletas.model.Inventario;
import com.tienda.bicicletas.model.Venta;
import com.tienda.bicicletas.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController{

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/ventas/dia")
    public List<Venta> ventasPorDia(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fecha){
             return reporteService.ventasPorDia(fecha);
    }

    @GetMapping("/ventas/rango")
    public List<Venta> ventasPorRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fin){

        return reporteService.ventasPorRango(inicio, fin);
    }

    @GetMapping("/stock")
    public List<Inventario> stockActual(){
        return reporteService.stockActual();
    }

}