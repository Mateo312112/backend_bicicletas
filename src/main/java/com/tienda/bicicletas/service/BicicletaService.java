package com.tienda.bicicletas.service;

import com.tienda.bicicletas.model.Bicicleta;
import com.tienda.bicicletas.repository.BicicletaRepository;
import com.tienda.bicicletas.repository.InventarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BicicletaService {

    @Autowired
    private BicicletaRepository bicicletaRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    public Bicicleta registrarBicicleta(Bicicleta bicicleta) {
        return bicicletaRepository.save(bicicleta);
    }

    public List<Bicicleta> listarBicicletas() {
        return bicicletaRepository.findAll();
    }

    public Optional<Bicicleta> buscarBicicleta(int idBicicleta) {
        return bicicletaRepository.findById(idBicicleta);
    }

    public Bicicleta actualizarBicicleta(int id, Bicicleta bicicleta) {
        bicicleta.setIdBicicleta(id);
        return bicicletaRepository.save(bicicleta);
    }

    @Transactional
    public void eliminar(Integer id) {
        Bicicleta bicicleta = bicicletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bicicleta no encontrada con ID: " + id));
        bicicletaRepository.delete(bicicleta);
    }

}