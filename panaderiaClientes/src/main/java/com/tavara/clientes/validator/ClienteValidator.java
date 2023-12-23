package com.tavara.clientes.validator;

import com.tavara.clientes.entity.Cliente;
import com.tavara.clientes.exceptions.ValidateServiceException;

public class ClienteValidator {
    public static void save(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            throw new ValidateServiceException("El nombre es Requerido");
        }
        if (cliente.getNombre().length() > 50) {
            throw new ValidateServiceException("El nombre es muy largo");
        }
        if (cliente.getApellido() == null || cliente.getApellido().isEmpty()) {
            throw new ValidateServiceException("El apellido es Requerido");
        }
        if (cliente.getApellido().length() > 50) {
            throw new ValidateServiceException("El apellido es muy largo");
        }
        if (cliente.getCorreo_electronico() == null || cliente.getCorreo_electronico().isEmpty()) {
            throw new ValidateServiceException("El correo electrónico es Requerido");
        }
        if (cliente.getTelefono() == null || cliente.getTelefono().isEmpty()) {
            throw new ValidateServiceException("El teléfono es Requerido");
        }
        if (cliente.getTelefono().length() > 15) {
            throw new ValidateServiceException("El teléfono es muy largo");
        }
        if (cliente.getDireccion() == null || cliente.getDireccion().isEmpty()) {
            throw new ValidateServiceException("La dirección es Requerida");
        }
        if (cliente.getDireccion().length() > 255) {
            throw new ValidateServiceException("La dirección es muy larga");
        }
    }
}