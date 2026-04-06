package com.tienda.bicicletas.controller;

import com.tienda.bicicletas.model.Bicicleta;
import com.tienda.bicicletas.service.BicicletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bicicletas")
@CrossOrigin(origins = "*")
public class BicicletaController {

    @Autowired
    private BicicletaService bicicletaService;

    @PostMapping
    public Bicicleta registrar(@RequestBody Bicicleta bicicleta) {
        return bicicletaService.registrarBicicleta(bicicleta);
    }

    @GetMapping
    public List<Bicicleta> listar() {
        return bicicletaService.listarBicicletas();
    }

    @GetMapping("/{idBicicleta}")
    public Optional<Bicicleta> buscar(@PathVariable int idBicicleta) {
        return bicicletaService.buscarBicicleta(idBicicleta);
    }

    @PutMapping("/{id}")
    public Bicicleta actualizar(@PathVariable int id, @RequestBody Bicicleta bicicleta) {
        return bicicletaService.actualizarBicicleta(id, bicicleta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        bicicletaService.eliminarBicicleta(id);
        return ResponseEntity.noContent().build();
    }
}