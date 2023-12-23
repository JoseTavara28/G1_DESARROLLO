package com.tenorio.empleado.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tenorio.empleado.entity.Empleado;
import com.tenorio.empleado.entity.Rol;

public interface EmpleadoService {
    public List<Empleado> findAll(Pageable page);
    public List<Empleado> findByNombre(String nombre, Pageable page);
    public Empleado findById(int id);
    public Empleado create(Empleado empleado);
    public Empleado update(Empleado empleado);
    
    public void delete(int id);
    public List<Rol>selectRol();
}