package com.tienda.bicicletas.controller;

import com.tienda.bicicletas.model.Venta;
import com.tienda.bicicletas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
public class VentaController{
    @Autowired
    private VentaService ventaService;

    @PostMapping
    public Venta registrar(@RequestBody Venta venta)
    {
        return  ventaService.registrarVenta(venta);
    }
    @GetMapping
    public List<Venta> listarVentas()
    {
        return ventaService.listarVentas();
    }
    @DeleteMapping("/{id}")
    public void eliminarVenta(@PathVariable int id)
    {
        ventaService.eliminarVenta(id);
    }

    @GetMapping("/{id}")
    public Optional<Venta> buscarVentaPorId(@PathVariable int id){
        return ventaService.buscarVenta(id);
    }

    @GetMapping("/cliente/{documento}")
    public List<Venta> buscarPorCliente(@PathVariable int documento){
        return ventaService.buscarPorCliente(documento);
    }

    @GetMapping("/rango")
    public List<Venta> buscarPorFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fin){

        return ventaService.buscarPorFechas(inicio, fin);
    }

}
