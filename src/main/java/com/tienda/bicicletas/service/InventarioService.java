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

    public Inventario registrarInventario(Inventario inventario){
        inventario.setUltimaActualizacion(LocalDate.now());
        return inventarioRepository.save(inventario);
    }

    public List<Inventario> listarInventario(){
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> buscarInventario(int idInventario){
        return inventarioRepository.findById(idInventario);

    }

    public Optional<Inventario> buscarPorBicicleta(int bicicleta){
        return inventarioRepository.findByBicicletaIdBicicleta(idbicicleta);
    }

    public Inventario actualizarInventario(int idbicicleta, int nuevaCantidad){
        Inventario inventario = inventarioRepository.findById(idbicicleta).orElseThrow(() => new RuntimeException("Inventario no encontrado para bicicleta: " + idbicicleta));

        inventario.setCantidadDisponible(nuevaCantidad);
        inventario.setUltimaActualizacion(LocalDate.now());
        return inventarioRepository.save(inventario);
    }

    public void eliminarInventario(int idbicicleta){
        inventarioRepository.deleteById(idbicicleta);
    }
}