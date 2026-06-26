package com.gameup.ticket_service.controller;

import com.gameup.ticket_service.assembler.TicketModelAssembler;
import com.gameup.ticket_service.dto.TicketRequestDTO;
import com.gameup.ticket_service.dto.TicketResponseDTO;
import com.gameup.ticket_service.service.TicketSoporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@Tag(name = "Tickets", description = "Métodos del microservicio de tickets de soporte")
public class TicketSoporteController {

    private final TicketSoporteService service;
    private final TicketModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Listar todos los tickets")
    public CollectionModel<EntityModel<TicketResponseDTO>> obtenerTodos() {
        List<EntityModel<TicketResponseDTO>> tickets = service.obtenerTodos()
                .stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(tickets, Link.of("/api/tickets").withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener ticket por ID")
    public EntityModel<TicketResponseDTO> obtenerPorId(
            @Parameter(description = "ID del ticket", required = true)
            @PathVariable Long id) {
        return assembler.toModel(service.obtenerPorId(id));
    }

    @GetMapping(value = "/usuario/{idUsuario}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Listar tickets de un usuario")
    public CollectionModel<EntityModel<TicketResponseDTO>> obtenerPorUsuario(
            @Parameter(description = "ID del usuario", required = true)
            @PathVariable Long idUsuario) {
        List<EntityModel<TicketResponseDTO>> tickets = service.obtenerPorUsuario(idUsuario)
                .stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(tickets,
                Link.of("/api/tickets/usuario/" + idUsuario).withSelfRel());
    }

    @GetMapping(value = "/estado/{estado}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Listar tickets por estado", description = "Estados: ABIERTO, PENDIENTE, CERRADO")
    public CollectionModel<EntityModel<TicketResponseDTO>> obtenerPorEstado(
            @Parameter(description = "Estado del ticket", required = true)
            @PathVariable String estado) {
        List<EntityModel<TicketResponseDTO>> tickets = service.obtenerPorEstado(estado)
                .stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(tickets,
                Link.of("/api/tickets/estado/" + estado).withSelfRel());
    }

    @PostMapping
    @Operation(summary = "Crear un ticket de soporte")
    public ResponseEntity<EntityModel<TicketResponseDTO>> guardar(
            @Valid @RequestBody TicketRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(assembler.toModel(service.guardar(dto)));
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Actualizar estado de un ticket")
    public EntityModel<TicketResponseDTO> actualizarEstado(
            @Parameter(description = "ID del ticket", required = true)
            @PathVariable Long id,
            @Parameter(description = "Nuevo estado: ABIERTO, PENDIENTE, CERRADO", required = true)
            @RequestParam String nuevoEstado) {
        return assembler.toModel(service.actualizarEstado(id, nuevoEstado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un ticket")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del ticket", required = true)
            @PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}