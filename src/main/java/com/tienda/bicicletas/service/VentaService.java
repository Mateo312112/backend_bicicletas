package com.tienda.bicicletas.service;

import com.tienda.bicicletas.model.Venta;
import com.tienda.bicicletas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service

public class VentaService
{
    @Autowired
    private VentaRepository ventaRepository;

    public Venta registrarVenta(Venta venta)
    {
        return ventaRepository.save(venta);
    }

    public List<Venta> listarVentas()
    {
        return ventaRepository.findAll();
    }

    public  Optional<Venta> buscarVenta(int idVenta)
    {
        return ventaRepository.findById(idVenta);
    }

    public List<Venta>buscarPorCliente(String documento)
    {
        return ventaRepository.findByClienteDocumento(documento);
    }

    public List<Venta>buscarPorFechas(LocalDate inicio, LocalDate fin)
    {
        return ventaRepository.findByFechaVentaBetween(inicio, fin);
    }

    public void eliminarVenta(int idVenta)
    {
        ventaRepository.deleteById(idVenta);
    }
}