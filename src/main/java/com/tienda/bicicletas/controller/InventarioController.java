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
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping("/bicicleta/{idBicicleta}")
    public Inventario buscarPorBicicleta(@PathVariable int idBicicleta) {
        return inventarioService.buscarPorBicicleta(idBicicleta)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado para bicicleta: " + idBicicleta));
    }

    @PostMapping
    public Inventario registrar(
            @RequestParam String idBicicleta,
            @RequestParam int cantidadInicial,
            @RequestParam int stockMinimo) {

        Integer id = Integer.parseInt(idBicicleta);
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