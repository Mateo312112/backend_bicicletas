package com.tienda.bicicletas.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VentaRequestDTO {
    private int documentoCliente;
    private LocalDate fechaVenta;
    private BigDecimal totalVenta;
}
