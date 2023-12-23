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
public class EmpleadoDTO {
    private int id;
    private String nombre;
    private String apellido;
    private String numeroCelular;
    private String direccion;
    private RolDTO rol;

    // Puedes agregar más propiedades según sea necesario
}
