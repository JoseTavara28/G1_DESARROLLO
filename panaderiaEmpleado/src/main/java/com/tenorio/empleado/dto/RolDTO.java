package com.tenorio.empleado.dto;

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
public class RolDTO {
    private int id;
    private String nombre;
    // Puedes agregar más atributos según tus necesidades para la entidad Rol
}