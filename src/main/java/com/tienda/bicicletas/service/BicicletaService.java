package com.tienda.bicicletas.service;


import com.tienda.bicicletas.model.Bicicleta;
import com.tienda.bicicletas.repository.BicicletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BicicletaService {

    @Autowired
    private BicicletaRepository bicicletaRepository;

    public Bicicleta registrarBicicleta(Bicicleta bicicleta) {
        return bicicletaRepository.save(bicicleta);
    }

    public List<Bicicleta> listarBicicletas() {
        return bicicletaRepository.findAll();
    }

    public Optional<Bicicleta> buscarBicicleta(int idBicicleta) {
        return bicicletaRepository.findById(idBicicleta);
    }
}