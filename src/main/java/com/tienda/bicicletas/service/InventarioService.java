package com.tienda.bicicletas.service;

import com.tienda.bicicletas.model.Inventario;
import com.tienda.bicicletas.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public Inventario registrarInventario(Inventario inventario) {
        int idBicicleta = inventario.getBicicleta().getIdBicicleta();

        Optional<Inventario> existente = inventarioRepository.findByBicicletaIdBicicleta(idBicicleta);

        if (existente.isPresent()) {
            Inventario inv = existente.get();
            inv.setCantidadDisponible(inventario.getCantidadDisponible());
            inv.setStockMinimo(inventario.getStockMinimo());
            inv.setUltimaActualizacion(LocalDate.now());
            return inventarioRepository.save(inv);
        }

        inventario.setUltimaActualizacion(LocalDate.now());
        return inventarioRepository.save(inventario);
    }

    public List<Inventario> listarInventario() {
        return inventarioRepository.findAllWithBicicleta();
    }

    public Optional<Inventario> buscarInventario(int idInventario) {
        return inventarioRepository.findById(idInventario);
    }

    public Optional<Inventario> buscarPorBicicleta(int idBicicleta) {
        return inventarioRepository.findByBicicletaIdBicicleta(idBicicleta);
    }

    public Inventario actualizarInventario(int idBicicleta, int nuevaCantidad) {
        Inventario inventario = inventarioRepository.findByBicicletaIdBicicleta(idBicicleta)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado para bicicleta: " + idBicicleta));

        inventario.setCantidadDisponible(nuevaCantidad);
        inventario.setUltimaActualizacion(LocalDate.now());
        return inventarioRepository.save(inventario);
    }

    public void eliminarInventario(int idInventario) {
        inventarioRepository.deleteById(idInventario);
    }
}