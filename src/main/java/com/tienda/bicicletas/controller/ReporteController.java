package com.tienda.bicicletas.controller;

import com.tienda.bicicletas.model.Inventario;
import com.tienda.bicicletas.model.Venta;
import com.tienda.bicicletas.model.DetalleVenta;
import com.tienda.bicicletas.service.ReporteService;
import com.tienda.bicicletas.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @GetMapping("/ventas-por-dia")
    public List<Map<String, Object>> ventasPorDia() {
        List<Venta> ventas = reporteService.ventasPorDia();

        // Si no hay ventas, devolver lista vacía
        if (ventas == null || ventas.isEmpty()) {
            return new ArrayList<>();
        }

        Map<LocalDate, Map<String, Object>> grouped = new LinkedHashMap<>();
        for (Venta v : ventas) {
            if (v.getFechaVenta() == null) continue;

            LocalDate fecha = v.getFechaVenta();
            if (!grouped.containsKey(fecha)) {
                Map<String, Object> item = new HashMap<>();
                item.put("fecha", fecha);
                item.put("cantidadVentas", 0L);
                item.put("totalRecaudado", BigDecimal.ZERO);
                grouped.put(fecha, item);
            }
            Map<String, Object> item = grouped.get(fecha);
            item.put("cantidadVentas", (Long) item.get("cantidadVentas") + 1);
            item.put("totalRecaudado", ((BigDecimal) item.get("totalRecaudado")).add(v.getTotalVenta()));
        }

        List<Map<String, Object>> result = new ArrayList<>(grouped.values());
        result.sort((a, b) -> ((LocalDate) b.get("fecha")).compareTo((LocalDate) a.get("fecha")));
        return result;
    }

    @GetMapping("/ventas-por-dia/rango")
    public List<Map<String, Object>> ventasPorDiaEnRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        List<Venta> ventas = reporteService.ventasPorRango(inicio, fin);

        Map<LocalDate, Map<String, Object>> grouped = new LinkedHashMap<>();
        for (Venta v : ventas) {
            LocalDate fecha = v.getFechaVenta();
            if (!grouped.containsKey(fecha)) {
                Map<String, Object> item = new HashMap<>();
                item.put("fecha", fecha);
                item.put("cantidadVentas", 0L);
                item.put("totalRecaudado", BigDecimal.ZERO);
                grouped.put(fecha, item);
            }
            Map<String, Object> item = grouped.get(fecha);
            item.put("cantidadVentas", (Long) item.get("cantidadVentas") + 1);
            item.put("totalRecaudado", ((BigDecimal) item.get("totalRecaudado")).add(v.getTotalVenta()));
        }

        List<Map<String, Object>> result = new ArrayList<>(grouped.values());
        result.sort((a, b) -> ((LocalDate) b.get("fecha")).compareTo((LocalDate) a.get("fecha")));
        return result;
    }

    @GetMapping("/top-productos")
    public List<Map<String, Object>> topProductos() {
        List<DetalleVenta> detalles = detalleVentaRepository.findAll();

        Map<Integer, Map<String, Object>> grouped = new LinkedHashMap<>();
        for (DetalleVenta d : detalles) {
            Integer id = d.getBicicleta().getIdBicicleta();
            if (!grouped.containsKey(id)) {
                Map<String, Object> item = new HashMap<>();
                item.put("idBicicleta", id);
                item.put("marca", d.getBicicleta().getMarca());
                item.put("modelo", d.getBicicleta().getModelo());
                item.put("totalUnidadesVendidas", 0L);
                grouped.put(id, item);
            }
            Map<String, Object> item = grouped.get(id);
            item.put("totalUnidadesVendidas", (Long) item.get("totalUnidadesVendidas") + d.getCantidad());
        }

        List<Map<String, Object>> result = new ArrayList<>(grouped.values());
        result.sort((a, b) -> ((Long) b.get("totalUnidadesVendidas")).compareTo((Long) a.get("totalUnidadesVendidas")));
        return result;
    }

    @GetMapping("/stock")
    public List<Map<String, Object>> stockActual() {
        List<Inventario> inventarios = reporteService.stockActual();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Inventario i : inventarios) {
            Map<String, Object> item = new HashMap<>();
            item.put("idInventario", i.getIdInventario());
            item.put("idBicicleta", i.getBicicleta().getIdBicicleta());
            item.put("codigo", i.getBicicleta().getCodigo());
            item.put("marca", i.getBicicleta().getMarca());
            item.put("modelo", i.getBicicleta().getModelo());
            item.put("cantidadDisponible", i.getCantidadDisponible());
            item.put("stockMinimo", i.getStockMinimo());
            item.put("bajoStockMinimo", i.getCantidadDisponible() <= i.getStockMinimo());
            result.add(item);
        }
        return result;
    }

    @GetMapping("/stock/critico")
    public List<Map<String, Object>> stockCritico() {
        List<Inventario> inventarios = reporteService.stockCritico();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Inventario i : inventarios) {
            Map<String, Object> item = new HashMap<>();
            item.put("idInventario", i.getIdInventario());
            item.put("idBicicleta", i.getBicicleta().getIdBicicleta());
            item.put("codigo", i.getBicicleta().getCodigo());
            item.put("marca", i.getBicicleta().getMarca());
            item.put("modelo", i.getBicicleta().getModelo());
            item.put("cantidadDisponible", i.getCantidadDisponible());
            item.put("stockMinimo", i.getStockMinimo());
            item.put("bajoStockMinimo", true);
            result.add(item);
        }
        return result;
    }
}