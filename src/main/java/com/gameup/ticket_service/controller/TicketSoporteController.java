package com.gameup.ticket_service.controller;

import com.gameup.ticket_service.dto.TicketSoporteRequestDTO;
import com.gameup.ticket_service.model.TicketSoporte;
import com.gameup.ticket_service.service.TicketSoporteService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/tickets")
public class TicketSoporteController {

    private final TicketSoporteService service;

    public TicketSoporteController(TicketSoporteService service) {
        this.service = service;
    }

    @GetMapping
    public List<TicketSoporte> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public EntityModel<TicketSoporte> obtenerPorId(@PathVariable Long id) {

        TicketSoporte ticket = service.obtenerPorId(id);

        EntityModel<TicketSoporte> recurso = EntityModel.of(ticket);

        recurso.add(
                linkTo(methodOn(TicketSoporteController.class)
                        .obtenerPorId(id))
                        .withSelfRel()
        );

        return recurso;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketSoporte guardar(@Valid @RequestBody TicketSoporteRequestDTO dto) {
        return service.guardar(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PatchMapping("/{id}/cerrar")
    public TicketSoporte cerrarTicket(@PathVariable Long id) {
        return service.cerrarTicket(id);
    }
}