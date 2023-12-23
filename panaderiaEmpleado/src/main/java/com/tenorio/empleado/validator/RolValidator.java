package com.tenorio.empleado.validator;

import com.tenorio.empleado.entity.Rol;
import com.tenorio.empleado.exceptions.ValidateServiceException;

public class RolValidator {
    public static void save(Rol rol) {
        if (rol.getNombre() == null || rol.getNombre().isEmpty()) {
            throw new ValidateServiceException("El nombre es requerido");
        }
        if (rol.getNombre().length() > 50) {
            throw new ValidateServiceException("El nombre es muy largo");
        }
        // Puedes agregar validaciones adicionales según tus requisitos para la entidad Rol
    }
}