package com.tavara.clientes.service;
import java.util.List;
import org.springframework.data.domain.Pageable;

import com.tavara.clientes.entity.Cliente;


public interface ClienteService {
	public List<Cliente> findAll(Pageable page);
	public List<Cliente> findByNombre(String nombre, Pageable page);
	public Cliente findById(int id);
	public Cliente create(Cliente cliente);
	public Cliente update(Cliente cliente);
	public void delete(int id);
}