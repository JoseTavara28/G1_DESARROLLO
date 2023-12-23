package com.tavara.clientes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tavara.clientes.entity.Cliente;
import com.tavara.clientes.exceptions.GeneralServiceException;
import com.tavara.clientes.exceptions.NoDataFoundException;
import com.tavara.clientes.exceptions.ValidateServiceException;
import com.tavara.clientes.repository.ClienteRepository;
import com.tavara.clientes.service.ClienteService;
import com.tavara.clientes.validator.ClienteValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll(Pageable page) {
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
    public List<Cliente> findByNombre(String nombre, Pageable page) {
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
    public Cliente findById(int id) {
        try {
            Cliente registro = repository.findById(id).orElseThrow(() -> new NoDataFoundException("NO existe ese registro con ese ID"));
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
    public Cliente create(Cliente cliente) {
        try {
            ClienteValidator.save(cliente);
            if (repository.findByNombre(cliente.getNombre()) != null) {
                throw new ValidateServiceException("Ya existe ese nombre" + cliente.getNombre());
            }
            
            if (repository.findByNombre(cliente.getCorreo_electronico())!= null) {
                throw new ValidateServiceException("El correo electrónico es Requerido");
            }

            Cliente registro = repository.save(cliente);
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
    public Cliente update(Cliente cliente) {
        try {
            ClienteValidator.save(cliente);
            Cliente registro = repository.findById(cliente.getId()).orElseThrow(() -> new NoDataFoundException("NO existe ese registro con ese ID"));
            Cliente registroD = repository.findByNombre(cliente.getNombre());
            if (registroD != null) {
                throw new ValidateServiceException("Ya existe un registro con ese nombre" + cliente.getNombre());
            }

            

            registro.setNombre(cliente.getNombre());
            registro.setApellido(cliente.getApellido());
            registro.setCorreo_electronico(cliente.getCorreo_electronico());
            registro.setTelefono(cliente.getTelefono());
            registro.setDireccion(cliente.getDireccion());

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
            Cliente registro = repository.findById(id).orElseThrow(() -> new NoDataFoundException("NO existe ese registro con ese ID"));
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
