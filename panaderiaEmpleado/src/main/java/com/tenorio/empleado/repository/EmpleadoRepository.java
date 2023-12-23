package com.tenorio.empleado.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tenorio.empleado.entity.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    List<Empleado> findByNombreContaining(String nombre, Pageable page);
    Empleado findByNombre(String nombre);
}
