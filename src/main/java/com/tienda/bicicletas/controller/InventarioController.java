package com.tienda.bicicletas.controller;

import com.tienda.bicicletas.model.Bicicleta;
import com.tienda.bicicletas.model.Inventario;
import com.tienda.bicicletas.repository.BicicletaRepository;
import com.tienda.bicicletas.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private BicicletaRepository bicicletaRepository;

    @GetMapping
    public List<Map<String, Object>> listar() {
        List<Inventario> inventarios = inventarioService.listarInventario();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Inventario i : inventarios) {
            Map<String, Object> item = new HashMap<>();
            item.put("idInventario", i.getIdInventario());
            item.put("cantidadDisponible", i.getCantidadDisponible());
            item.put("stockMinimo", i.getStockMinimo());
            item.put("ultimaActualizacion", i.getUltimaActualizacion());

            // Agregar datos de la bicicleta
            if (i.getBicicleta() != null) {
                item.put("idBicicleta", i.getBicicleta().getIdBicicleta());
                item.put("codigo", i.getBicicleta().getCodigo());
                item.put("marca", i.getBicicleta().getMarca());
                item.put("modelo", i.getBicicleta().getModelo());
                item.put("tipo", i.getBicicleta().getTipo());
                item.put("precioLista", i.getBicicleta().getPrecioLista());
            }

            result.add(item);
        }
        return result;
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