package com.tienda.bicicletas.controller;

import com.tienda.bicicletas.model.Cliente;
import com.tienda.bicicletas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente registrar(@RequestBody Cliente cliente) {
        return clienteService.registrarCliente(cliente);
    }

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{documento}")
    public Optional<Cliente> buscar(@PathVariable int documento) {
        return clienteService.buscarCliente(documento);
    }
}