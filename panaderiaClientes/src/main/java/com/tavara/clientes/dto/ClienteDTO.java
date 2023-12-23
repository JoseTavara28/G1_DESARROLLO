package com.tavara.clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClienteDTO {
    private int id;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String telefono;
    private String direccion;
}