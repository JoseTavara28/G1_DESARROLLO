package com.tavara.clientes.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavara.clientes.entity.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNombreContaining(String nombre, Pageable page);
    Cliente findByNombre(String nombre);
}
