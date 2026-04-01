package com.tienda.bicicletas.repository;
import com.tienda.bicicletas.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InventarioRepository  extends JpaRepository<Inventario,Integer> {

        Optional<Inventario> findByBicicleta(int idBicicleta);

}
