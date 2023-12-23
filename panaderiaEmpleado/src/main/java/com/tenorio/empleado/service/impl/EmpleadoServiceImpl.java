package com.tenorio.empleado.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenorio.empleado.entity.Empleado;
import com.tenorio.empleado.entity.Rol;
import com.tenorio.empleado.exceptions.GeneralServiceException;
import com.tenorio.empleado.exceptions.NoDataFoundException;
import com.tenorio.empleado.exceptions.ValidateServiceException;
import com.tenorio.empleado.repository.EmpleadoRepository;
import com.tenorio.empleado.repository.RolRepository;
import com.tenorio.empleado.service.EmpleadoService;
import com.tenorio.empleado.validator.EmpleadoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpleadoServiceImpl implements EmpleadoService {  // Cambié el nombre del servicio a EmpleadoServiceImpl

    @Autowired
    private EmpleadoRepository repository;  // Cambié la inyección de dependencia al repositorio EmpleadoRepository

    @Autowired
    private RolRepository rolrepository;
    
    
    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findByNombre(String nombre, Pageable page) {
        try {
            return repository.findByNombreContaining(nombre, page);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findById(int id) {
        try {
            Empleado registro = repository.findById(id).orElseThrow(() -> new NoDataFoundException("No existe ese registro con ese ID"));
            return registro;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Empleado create(Empleado empleado) {
        try {
            EmpleadoValidator.save(empleado);
            if (repository.findByNombre(empleado.getNombre()) != null) {
                throw new ValidateServiceException("Ya existe ese nombre" + empleado.getNombre());
            }

            // Comenté la validación del correo electrónico porque no está en la tabla Empleados

            Empleado registro = repository.save(empleado);
            return registro;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Empleado update(Empleado empleado) {
        try {
            EmpleadoValidator.save(empleado);
            Empleado registro = repository.findById(empleado.getId()).orElseThrow(() -> new NoDataFoundException("No existe ese registro con ese ID"));
            Empleado registroD = repository.findByNombre(empleado.getNombre());
            if (registroD != null) {
                throw new ValidateServiceException("Ya existe un registro con ese nombre" + empleado.getNombre());
            }

            registro.setNombre(empleado.getNombre());
            registro.setApellido(empleado.getApellido());
            registro.setNumeroCelular(empleado.getNumeroCelular());
            registro.setDireccion(empleado.getDireccion());
            registro.setRol(empleado.getRol());

            repository.save(registro);
            return registro;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Empleado registro = repository.findById(id).orElseThrow(() -> new NoDataFoundException("No existe ese registro con ese ID"));
            repository.delete(registro);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
    
    
	@Override
	@Transactional(readOnly = true)
	public List<Rol> selectRol() {
		try {
            return rolrepository.findAll();
        } catch (Exception e) {
            log.error("Error al recuperar las categorías", e);
            throw new GeneralServiceException("Error al recuperar las categorías", e);
        }
	}

}

