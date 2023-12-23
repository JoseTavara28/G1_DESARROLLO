package com.tenorio.empleado.converter;

import org.springframework.stereotype.Component;

import com.tenorio.empleado.dto.RolDTO;
import com.tenorio.empleado.entity.Rol;

@Component
public class RolConverter extends AbstractConvert<Rol, RolDTO> {
    @Override
    public RolDTO fromEntity(Rol entity) {
        if (entity == null) return null;
        return RolDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }

    @Override
    public Rol fromDTO(RolDTO dto) {
        if (dto == null) return null;
        return Rol.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .build();
    }
}
