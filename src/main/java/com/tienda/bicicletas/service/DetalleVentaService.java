package com.tienda.bicicletas.service;

import com.tienda.bicicletas.model.DetalleVenta;
import com.tienda.bicicletas.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public DetalleVenta registrarDetalle(DetalleVenta detalle) {
        return detalleVentaRepository.save(detalle);
    }

    public List<DetalleVenta> listarDetalles() {
        return detalleVentaRepository.findAll();
    }

    public Optional<DetalleVenta> buscarDetalle(int idDetalle) {
        return detalleVentaRepository.findById(idDetalle);
    }
}