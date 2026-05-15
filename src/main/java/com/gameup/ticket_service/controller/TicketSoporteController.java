package com.gameup.ticket_service.controller;

import com.gameup.ticket_service.dto.TicketRequestDTO;
import com.gameup.ticket_service.model.TicketSoporte;
import com.gameup.ticket_service.service.TicketSoporteService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public TicketSoporte guardar(@RequestBody TicketRequestDTO dto) {
        return service.guardar(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
