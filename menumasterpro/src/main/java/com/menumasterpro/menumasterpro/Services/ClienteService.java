package com.menumasterpro.menumasterpro.Services;


import com.menumasterpro.menumasterpro.Common.CustomException;
import com.menumasterpro.menumasterpro.Models.Cliente;
import com.menumasterpro.menumasterpro.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los Clientes
    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    // Obtener cliente por nombre
    public List<Cliente> obtenerClientePorNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    // Crear un nuevo cliente
    public String crearCliente(Cliente cliente) {
        Cliente c = clienteRepository.findByTelefono(cliente.getTelefono());
        if ( c == null) {
            clienteRepository.save(cliente);
            return "El cliente se ha creado exitosamente";
        } else  {
            throw new CustomException(HttpStatus.CONFLICT.value(), "El cliente ya existe");
        }
    }

    // Actualizar un cliente
    public String actualizarCliente(Cliente cliente) {
        Cliente c = clienteRepository.getReferenceById(cliente.getIdCliente());
        if ( c != null) {
            c.setNombre(cliente.getNombre());
            c.setTelefono(cliente.getTelefono());
            c.setDireccion(cliente.getDireccion());
            c.setCorreo(cliente.getCorreo());
            clienteRepository.save(c);
            return "El cliente se ha modificado exitosamente";
        }
        else {
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "El cliente no existe");
        }
    }

    // Eliminar un cliente
    public String eliminarCliente(String idCliente) {
        Cliente c = clienteRepository.getReferenceById(Integer.valueOf(idCliente));
        if (c != null) {
            clienteRepository.delete(c);
            return "El cliente se ha borrado exitosamente";
        }
        else {
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "El cliente no existe");
        }
    }

}
