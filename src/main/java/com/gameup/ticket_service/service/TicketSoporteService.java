package com.gameup.ticket_service.service;

import com.gameup.ticket_service.dto.TicketRequestDTO;
import com.gameup.ticket_service.dto.TicketResponseDTO;
import com.gameup.ticket_service.exception.ResourceNotFoundException;
import com.gameup.ticket_service.model.TicketSoporte;
import com.gameup.ticket_service.repository.TicketSoporteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketSoporteService {

    private final TicketSoporteRepository repository;

    public List<TicketResponseDTO> obtenerTodos() {
        return repository.findAll().stream()
                .map(this::mapToResponse).toList();
    }

    public TicketResponseDTO obtenerPorId(Long id) {
        TicketSoporte ticket = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado con id: " + id));
        return mapToResponse(ticket);
    }

    public List<TicketResponseDTO> obtenerPorUsuario(Long idUsuario) {
        return repository.findByIdUsuario(idUsuario).stream()
                .map(this::mapToResponse).toList();
    }

    public List<TicketResponseDTO> obtenerPorEstado(String estado) {
        return repository.findByEstado(estado.toUpperCase()).stream()
                .map(this::mapToResponse).toList();
    }

    public TicketResponseDTO guardar(TicketRequestDTO dto) {
        TicketSoporte ticket = TicketSoporte.builder()
                .idUsuario(dto.getIdUsuario())
                .asunto(dto.getAsunto())
                .descripcion(dto.getDescripcion())
                .estado(dto.getEstado() != null ? dto.getEstado().toUpperCase() : "ABIERTO")
                .fechaCreacion(dto.getFechaCreacion())
                .build();
        return mapToResponse(repository.save(ticket));
    }

    public TicketResponseDTO actualizarEstado(Long id, String nuevoEstado) {
        TicketSoporte ticket = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado con id: " + id));
        ticket.setEstado(nuevoEstado.toUpperCase());
        return mapToResponse(repository.save(ticket));
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Ticket no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private TicketResponseDTO mapToResponse(TicketSoporte t) {
        return TicketResponseDTO.builder()
                .idTicket(t.getIdTicket())
                .idUsuario(t.getIdUsuario())
                .asunto(t.getAsunto())
                .descripcion(t.getDescripcion())
                .estado(t.getEstado())
                .fechaCreacion(t.getFechaCreacion())
                .build();
    }
}