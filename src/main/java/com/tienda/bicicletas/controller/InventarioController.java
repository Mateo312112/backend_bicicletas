package com.tienda.bicicletas.controller;

import com.tienda.bicicletas.model.Bicicleta;
import com.tienda.bicicletas.model.Inventario;
import com.tienda.bicicletas.repository.BicicletaRepository;
import com.tienda.bicicletas.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private BicicletaRepository bicicletaRepository;

    @GetMapping
    public List<Inventario> listar() {
        return inventarioService.listarInventario();
    }

    @GetMapping("/alerta")
    public List<Inventario> alertas() {
        return inventarioService.listarInventario().stream()
                .filter(i -> i.getCantidadDisponible() <= i.getStockMinimo())
                .collect(Collectors.toList());
    }

    @PostMapping
    public Inventario registrar(
            @RequestParam String idBicicleta,
            @RequestParam int cantidadInicial,
            @RequestParam int stockMinimo) {

        Integer id = Integer.parseInt(idBicicleta); // ← aquí
        Bicicleta bicicleta = bicicletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bicicleta no encontrada: " + id));

        Inventario inventario = new Inventario();
        inventario.setBicicleta(bicicleta);
        inventario.setCantidadDisponible(cantidadInicial);
        inventario.setStockMinimo(stockMinimo);

        return inventarioService.registrarInventario(inventario);
    }

    @PutMapping("/bicicleta/{idBicicleta}")
    public Inventario actualizarCantidad(
            @PathVariable int idBicicleta,
            @RequestParam int nuevaCantidad) {
        return inventarioService.actualizarInventario(idBicicleta, nuevaCantidad);
    }
}