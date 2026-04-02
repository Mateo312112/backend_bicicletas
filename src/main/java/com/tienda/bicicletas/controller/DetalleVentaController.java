package com.tienda.bicicletas.controller;

import com.tienda.bicicletas.model.DetalleVenta;
import com.tienda.bicicletas.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalles-ventas")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @PostMapping
    public DetalleVenta registrar(@RequestBody DetalleVenta detalle) {
        return detalleVentaService.registrarDetalle(detalle);
    }

    @GetMapping
    public List<DetalleVenta> listar() {
        return detalleVentaService.listarDetalles();
    }

    @GetMapping("/{idDetalle}")
    public Optional<DetalleVenta> buscar(@PathVariable int idDetalle) {
        return detalleVentaService.buscarDetalle(idDetalle);
    }
}