package com.tavara.clientes.converter;

import org.springframework.stereotype.Component;

import com.tavara.clientes.dto.ClienteDTO;
import com.tavara.clientes.entity.Cliente;



@Component
public class ClienteConverter extends AbstractConvert<Cliente, ClienteDTO> {
    @Override
    public ClienteDTO fromEntity(Cliente entity) {
        if (entity == null) return null;
        return ClienteDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .correoElectronico(entity.getCorreo_electronico())
                .telefono(entity.getTelefono())
                .direccion(entity.getDireccion())
                .build();
    }

    @Override
    public Cliente fromDTO(ClienteDTO dto) {
        if (dto == null) return null;
        return Cliente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .correo_electronico(dto.getCorreoElectronico())
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion())
                .build();
    }
}