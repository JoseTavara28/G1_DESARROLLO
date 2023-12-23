package com.tenorio.empleado.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.tenorio.empleado.entity.Rol;

public interface RolService {
    List<Rol> findAll(Pageable page);
    List<Rol> findByNombre(String nombre, Pageable page);
    Rol findById(int id);
    Rol save(Rol rol);
    Rol update(Rol rol);
    void delete(int id);
}