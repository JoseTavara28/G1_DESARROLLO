package com.tenorio.empleado.validator;

import com.tenorio.empleado.entity.Empleado;
import com.tenorio.empleado.exceptions.ValidateServiceException;

public class EmpleadoValidator {
    public static void save(Empleado empleado) {
        if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
            throw new ValidateServiceException("El nombre es requerido");
        }
        if (empleado.getNombre().length() > 50) {
            throw new ValidateServiceException("El nombre es muy largo");
        }
        if (empleado.getApellido() == null || empleado.getApellido().isEmpty()) {
            throw new ValidateServiceException("El apellido es requerido");
        }
        if (empleado.getApellido().length() > 50) {
            throw new ValidateServiceException("El apellido es muy largo");
        }
        if (empleado.getDireccion() == null || empleado.getDireccion().isEmpty()) {
            throw new ValidateServiceException("La dirección es requerida");
        }
        // Puedes ajustar las validaciones adicionales según tus necesidades para el número de celular
        if (empleado.getNumeroCelular() != null && empleado.getNumeroCelular().length() > 15) {
            throw new ValidateServiceException("El número de celular es muy largo");
        }
    }
}

