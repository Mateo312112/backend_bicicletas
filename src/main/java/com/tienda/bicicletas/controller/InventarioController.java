package com.tienda.bicicletas.controller;

import com.tienda.bicicletas.model.Inventario;
import com.tienda.bicicletas.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    public Inventario registrar(@RequestBody Inventario inventario) {
        return inventarioService.registrarInventario(inventario);

    }

    @GetMapping
    public List<Inventario> listar() {
        return inventarioService.listarInventario();
    }

    @GetMapping("/{idInventario}")
    public Optional<Inventario> buscar(@PathVariable int idInventario) {
        return inventarioService.buscarInventario(idInventario);
    }

    @GetMapping("/bicicleta/{idBicicleta}")
    public Optional<Inventario> buscarPorBicicleta(@PathVariable int idBicicleta) {
        return inventarioService.buscarInventario(idBicicleta);
    }

    @PutMapping
    public Inventario actualizarcantidad(@PathVariable int idBicicleta, @RequestBody int nuevaCantidad) {
        return inventarioService.actualizarInventario(idBicicleta, nuevaCantidad);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        inventarioService.eliminarInventario(id);
    }

}