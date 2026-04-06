package com.tienda.bicicletas.service;

import com.tienda.bicicletas.repository.ClienteRepository;
import com.tienda.bicicletas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tienda.bicicletas.model.Cliente;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente registrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarCliente(String documento) {
        return clienteRepository.findById(documento);
    }

    public Cliente actualizarCliente(String documento, Cliente cliente) {
        cliente.setDocumento(documento);
        return clienteRepository.save(cliente);
    }

    @Autowired
    private VentaRepository ventaRepository;

    public void eliminarCliente(String documento) {
        ventaRepository.findByClienteDocumento(documento)
                .forEach(venta -> ventaRepository.delete(venta));
        clienteRepository.deleteById(documento);
    }
}