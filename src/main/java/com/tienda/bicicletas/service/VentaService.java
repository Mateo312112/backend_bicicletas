package com.tienda.bicicletas.service;

import com.tienda.bicicletas.dto.VentaRequestDTO;
import com.tienda.bicicletas.model.*;
import com.tienda.bicicletas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired private VentaRepository ventaRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private BicicletaRepository bicicletaRepository;
    @Autowired private InventarioRepository inventarioRepository;  // ← Agrega esta línea

    public List<Venta> listarVentas() { return ventaRepository.findAll(); }

    public Optional<Venta> buscarVenta(int idVenta) { return ventaRepository.findById(idVenta); }

    public List<Venta> buscarPorCliente(String documento) { return ventaRepository.findByClienteDocumento(documento); }

    public List<Venta> buscarPorFechas(LocalDate inicio, LocalDate fin) { return ventaRepository.findByFechaVentaBetween(inicio, fin); }

    public void eliminarVenta(int idVenta) { ventaRepository.deleteById(idVenta); }

    @Transactional
    public Venta registrarVenta(VentaRequestDTO request) {
        Cliente cliente = clienteRepository.findById(request.getDocumentoCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + request.getDocumentoCliente()));

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFechaVenta(LocalDate.now());

        List<DetalleVenta> detalles = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (VentaRequestDTO.ItemVentaDTO item : request.getItems()) {
            Bicicleta bicicleta = bicicletaRepository.findById(item.getIdBicicleta())
                    .orElseThrow(() -> new RuntimeException("Bicicleta no encontrada: " + item.getIdBicicleta()));

            // ========== VERIFICAR Y ACTUALIZAR INVENTARIO ==========
            Inventario inventario = inventarioRepository.findByBicicletaIdBicicleta(item.getIdBicicleta())
                    .orElseThrow(() -> new RuntimeException("Inventario no encontrado para bicicleta ID: " + item.getIdBicicleta()));

            if (inventario.getCantidadDisponible() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente. Disponible: " + inventario.getCantidadDisponible() +
                        ", Solicitado: " + item.getCantidad());
            }

            // RESTAR DEL INVENTARIO
            inventario.setCantidadDisponible(inventario.getCantidadDisponible() - item.getCantidad());
            inventarioRepository.save(inventario);
            // =======================================================

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setBicicleta(bicicleta);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitarioVenta(bicicleta.getPrecioLista());
            detalle.setSubtotal(bicicleta.getPrecioLista() * item.getCantidad());

            total = total.add(BigDecimal.valueOf(bicicleta.getPrecioLista() * item.getCantidad()));
            detalles.add(detalle);
        }

        venta.setTotalVenta(total);
        venta.setDetallesVenta(detalles);
        return ventaRepository.save(venta);
    }
}