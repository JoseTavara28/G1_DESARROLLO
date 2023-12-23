package com.tenorio.empleado.entity;


import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Empleados")  
@EntityListeners(AuditingEntityListener.class)
public class Empleado {  // Cambié el nombre de la clase a "Empleado"
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;
    
    @Column(name = "direccion", length = 100, nullable = false)  // Puedes dejar la dirección como nullable si lo prefieres
    private String direccion;
    
    @Column(name = "numero_celular", length = 15,nullable = false)  // Puedes dejar el número de celular como nullable si lo prefieres
    private String numeroCelular;
    
    
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
    
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
    
    @ManyToOne()
    @JoinColumn(name = "rol_id") 
    private Rol rol;

}