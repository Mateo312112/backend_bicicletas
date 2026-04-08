package com.tienda.bicicletas.controller;

import com.tienda.bicicletas.model.Cliente;
import com.tienda.bicicletas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
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
    public Cliente buscar(@PathVariable String documento) {
        return clienteService.buscarCliente(documento)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + documento));
    }

    @DeleteMapping("/{documento}")
    public void eliminar(@PathVariable String documento) {
        clienteService.eliminarCliente(documento);
    }

    @PutMapping("/{documento}")
    public Cliente actualizar(@PathVariable String documento, @RequestBody Cliente cliente) {
        return clienteService.actualizarCliente(documento, cliente);
    }
}