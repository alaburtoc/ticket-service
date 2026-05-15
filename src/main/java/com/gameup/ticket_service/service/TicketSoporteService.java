package com.gameup.ticket_service.service;

import com.gameup.ticket_service.dto.TicketRequestDTO;
import com.gameup.ticket_service.model.TicketSoporte;
import com.gameup.ticket_service.repository.TicketSoporteRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketSoporteService {

    private final TicketSoporteRepository repository;

    public TicketSoporteService(TicketSoporteRepository repository) {
        this.repository = repository;
    }

    public List<TicketSoporte> obtenerTodos() {
        return repository.findAll();
    }

    public TicketSoporte guardar(TicketRequestDTO dto) {

        TicketSoporte ticket = new TicketSoporte();

        ticket.setIdUsuario(dto.getIdUsuario());
        ticket.setAsunto(dto.getAsunto());
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setEstado(dto.getEstado());
        ticket.setFechaCreacion(dto.getFechaCreacion());

        return repository.save(ticket);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
