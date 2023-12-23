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

import com.tenorio.empleado.converter.RolConverter;
import com.tenorio.empleado.service.RolService;
import com.tenorio.empleado.utils.WrapperResponse;
import com.tenorio.empleado.dto.RolDTO;
import com.tenorio.empleado.entity.Rol;

@RestController
@RequestMapping("/roles") // Cambiado de "/categorias" a "/roles"
public class RolController {
    @Autowired
    private RolService service;

    @Autowired
    private RolConverter converter;

    @GetMapping()
    public ResponseEntity<List<RolDTO>> findAll(
            @RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Rol> roles;
        if (nombre == null) {
            roles = service.findAll(page);
        } else {
            roles = service.findByNombre(nombre, page);
        }
        List<RolDTO> rolesDTO = converter.fromEntity(roles);
        return new WrapperResponse(true, "success", rolesDTO).createRespose(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WrapperResponse<RolDTO>> findById(@PathVariable("id") int id) {
        Rol rol = service.findById(id);
        RolDTO rolDTO = converter.fromEntity(rol);
        return new WrapperResponse<RolDTO>(true, "success", rolDTO).createRespose(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RolDTO> create(@RequestBody RolDTO rolDTO) {
        Rol registro = service.save(converter.fromDTO(rolDTO));
        RolDTO registroDTO = converter.fromEntity(registro);
        return new WrapperResponse(true, "success", registroDTO).createRespose(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RolDTO> update(@PathVariable("id") int id, @RequestBody RolDTO rolDTO) {
        Rol registro = service.update(converter.fromDTO(rolDTO));
        RolDTO registroDTO = converter.fromEntity(registro);
        return new WrapperResponse(true, "success", registroDTO).createRespose(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<RolDTO> delete(@PathVariable("id") int id) {
        service.delete(id);
        return new WrapperResponse(true, "success", null).createRespose(HttpStatus.OK);
    }
}
