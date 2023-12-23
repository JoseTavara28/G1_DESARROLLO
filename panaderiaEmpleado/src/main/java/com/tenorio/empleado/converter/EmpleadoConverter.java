package com.tenorio.empleado.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tenorio.empleado.dto.EmpleadoDTO;
import com.tenorio.empleado.entity.Empleado;



@Component
public class EmpleadoConverter extends AbstractConvert<Empleado, EmpleadoDTO> {
	@Autowired
	private RolConverter rolConverter;
    @Override
    public EmpleadoDTO fromEntity(Empleado entity) {
        if (entity == null) return null;
        return EmpleadoDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .numeroCelular(entity.getNumeroCelular()) // Asumí que el número de celular se mapea a "telefono" en el DTO
                .direccion(entity.getDireccion())
                .rol(rolConverter.fromEntity(entity.getRol()))
                .build();
    }

    @Override
    public Empleado fromDTO(EmpleadoDTO dto) {
        if (dto == null) return null;
        return Empleado.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .numeroCelular(dto.getNumeroCelular()) // Asumí que el número de celular se mapea a "numeroCelular" en la entidad
                .direccion(dto.getDireccion())
                .rol(rolConverter.fromDTO(dto.getRol()))
                .build();
    }
}
