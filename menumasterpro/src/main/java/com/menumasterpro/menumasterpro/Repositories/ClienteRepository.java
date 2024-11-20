package com.menumasterpro.menumasterpro.Repositories;

import com.menumasterpro.menumasterpro.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Metodos personalizados para buscar por nombre y telefono
    List<Cliente> findByNombre(String nombre);
    Cliente findByTelefono(String telefono);

}
