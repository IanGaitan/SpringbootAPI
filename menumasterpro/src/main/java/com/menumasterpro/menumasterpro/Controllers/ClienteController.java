package com.menumasterpro.menumasterpro.Controllers;

import com.menumasterpro.menumasterpro.Common.CustomException;
import com.menumasterpro.menumasterpro.Models.Cliente;
import com.menumasterpro.menumasterpro.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/cliente/")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Obtener todos los clientes
    // Ruta: localhost:8080/cliente/obtenerclientes
    @GetMapping(path = "obtenerclientes")
    public ResponseEntity<List<Cliente>> obtenerClientes(){
        List<Cliente> clientes = clienteService.obtenerClientes();
        if(clientes != null){
            return ResponseEntity.ok(clientes);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    // Obtener un cliente especifico por nombre
    // Ruta: localhost:8080/cliente/obtenercliente
    @GetMapping(path = "obtenercliente")
    public ResponseEntity<List<Cliente>> obtenerClientePorNombre(@RequestParam String nombre){
        List<Cliente> clientes = clienteService.obtenerClientePorNombre(nombre);
        if(clientes != null){
            return ResponseEntity.ok(clientes);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    // Crear un nuevo cliente
    // Ruta: localhost:8080/cliente/crearcliente
    @PostMapping(path = "crearcliente")
    public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente){
        try {
            String nuevoCliente = clienteService.crearCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());
        }
    }

    // Actualizar un cliente
    // Ruta: localhost:8080/cliente/actualizarcliente
    @PutMapping(path = "actualizarcliente")
    public ResponseEntity<String> actualizarCliente(@RequestBody Cliente cliente){
        try {
            String actCliente = clienteService.actualizarCliente(cliente);
            return ResponseEntity.ok(actCliente);
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatus()).body(e.toString());
        }
    }

    // Eliminar un Cliente
    // Ruta: localhost:8080/cliente/eliminarcliente
    @DeleteMapping(path = "eliminarcliente")
    public ResponseEntity<String> eliminarCliente(@RequestParam String idCliente){
        try {
            String delCliente = clienteService.eliminarCliente(idCliente);
            return ResponseEntity.ok(delCliente);
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatus()).body(e.toString());
        }
    }

}
