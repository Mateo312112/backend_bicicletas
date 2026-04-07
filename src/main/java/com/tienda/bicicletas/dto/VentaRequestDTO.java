package com.tienda.bicicletas.dto;

import lombok.Data;
import java.util.List;

@Data
public class VentaRequestDTO {
    private String documentoCliente;
    private List<ItemVentaDTO> items;

    @Data
    public static class ItemVentaDTO {
        private Integer idBicicleta;
        private Integer cantidad;
    }
}