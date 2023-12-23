package com.tenorio.empleado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenorio.empleado.converter.EmpleadoConverter;
import com.tenorio.empleado.dto.EmpleadoDTO;
import com.tenorio.empleado.entity.Empleado;
import com.tenorio.empleado.service.EmpleadoService;
import com.tenorio.empleado.utils.WrapperResponse;

@RestController
@RequestMapping("/empleados")  // Cambié la ruta a "/empleados"
public class EmpleadoController {
    @Autowired
    private EmpleadoService service;

    @Autowired
    private EmpleadoConverter converter;

    @GetMapping()
    public ResponseEntity<List<EmpleadoDTO>> findAll(
            @RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Empleado> empleados;
        if (nombre == null || nombre.isEmpty()) {
            empleados = service.findAll(page);
        } else {
            empleados = service.findByNombre(nombre, page);
        }
        List<EmpleadoDTO> empleadosDTO = converter.fromEntity(empleados);
        return new WrapperResponse(true, "success", empleadosDTO).createRespose(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WrapperResponse<EmpleadoDTO>> findById(@PathVariable("id") int id) {
        Empleado empleado = service.findById(id);
        EmpleadoDTO empleadoDTO = converter.fromEntity(empleado);
        return new WrapperResponse<EmpleadoDTO>(true, "success", empleadoDTO).createRespose(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> create(@RequestBody EmpleadoDTO empleadoDTO) {
        Empleado registro = service.create(converter.fromDTO(empleadoDTO));
        EmpleadoDTO registroDTO = converter.fromEntity(registro);
        return new WrapperResponse(true, "success", registroDTO).createRespose(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmpleadoDTO> update(@PathVariable("id") int id, @RequestBody EmpleadoDTO empleadoDTO) {
        Empleado registro = service.update(converter.fromDTO(empleadoDTO));
        EmpleadoDTO registroDTO = converter.fromEntity(registro);
        return new WrapperResponse(true, "success", registroDTO).createRespose(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EmpleadoDTO> delete(@PathVariable("id") int id) {
        service.delete(id);
        return new WrapperResponse(true, "success", null).createRespose(HttpStatus.OK);
    }
}
