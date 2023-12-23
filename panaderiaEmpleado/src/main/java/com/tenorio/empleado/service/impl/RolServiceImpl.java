package com.tenorio.empleado.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenorio.empleado.entity.Rol;
import com.tenorio.empleado.exceptions.GeneralServiceException;
import com.tenorio.empleado.exceptions.NoDataFoundException;
import com.tenorio.empleado.exceptions.ValidateServiceException;
import com.tenorio.empleado.repository.RolRepository;
import com.tenorio.empleado.service.RolService;
import com.tenorio.empleado.validator.RolValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RolServiceImpl implements RolService {
    @Autowired
    private RolRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Rol> findAll(Pageable page) {
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
    public List<Rol> findByNombre(String nombre, Pageable page) {
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
    public Rol findById(int id) {
        try {
            Rol registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe ese registro con ese ID"));
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
    public Rol save(Rol rol) {
        try {
            RolValidator.save(rol);
            if (repository.findByNombre(rol.getNombre()) != null) {
                throw new ValidateServiceException("Ya existe ese nombre" + rol.getNombre());
            }
            rol.setActivo(true);
            Rol registro = repository.save(rol);
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
    public Rol update(Rol rol) {
        try {
            RolValidator.save(rol);
            Rol registro = repository.findById(rol.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe ese registro con ese ID"));
            Rol registroD = repository.findByNombre(rol.getNombre());
            if (registroD != null) {
                throw new ValidateServiceException("Ya existe un registro con ese nombre" + rol.getNombre());
            }
            registro.setNombre(rol.getNombre());
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
            Rol registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe ese registro con ese ID"));
            repository.delete(registro);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}