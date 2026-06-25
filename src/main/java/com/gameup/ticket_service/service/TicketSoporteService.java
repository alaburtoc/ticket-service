package com.gameup.ticket_service.service;

import com.gameup.ticket_service.client.AdminFeignClient;
import com.gameup.ticket_service.dto.AdminResponseDTO;
import com.gameup.ticket_service.dto.TicketSoporteRequestDTO;
import com.gameup.ticket_service.exception.ResourceNotFoundException;
import com.gameup.ticket_service.model.TicketSoporte;
import com.gameup.ticket_service.repository.TicketSoporteRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketSoporteService {

    private final TicketSoporteRepository repository;
    private final AdminFeignClient adminFeignClient;

    public TicketSoporteService(TicketSoporteRepository repository,
                                AdminFeignClient adminFeignClient) {
        this.repository = repository;
        this.adminFeignClient = adminFeignClient;
    }

    public List<TicketSoporte> obtenerTodos() {
        return repository.findAll();
    }

    // NUEVO MÉTODO PARA HATEOAS
    public TicketSoporte obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ticket no encontrado con id: " + id));
    }

    public TicketSoporte guardar(TicketSoporteRequestDTO dto) {

        AdminResponseDTO admin;
        try {
            admin = adminFeignClient.obtenerAdminPorId(dto.getIdAdmin());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Admin no encontrado con id: " + dto.getIdAdmin());
        } catch (FeignException e) {
            throw new RuntimeException("No se puede conectar con admin-service: " + e.getMessage());
        }

        if (!admin.getActivo()) {
            throw new RuntimeException("El admin con id " + dto.getIdAdmin() + " no está activo");
        }

        TicketSoporte ticket = new TicketSoporte();
        ticket.setIdUsuario(dto.getIdUsuario());
        ticket.setIdAdmin(dto.getIdAdmin());
        ticket.setAsunto(dto.getAsunto());
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setEstado("ABIERTO");
        ticket.setFechaCreacion(LocalDate.now());

        return repository.save(ticket);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Ticket no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    public TicketSoporte cerrarTicket(Long id) {
        TicketSoporte ticket = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Ticket no encontrado con id: " + id));

        if ("CERRADO".equals(ticket.getEstado())) {
            throw new RuntimeException("El ticket con id " + id + " ya está cerrado");
        }

        ticket.setEstado("CERRADO");
        return repository.save(ticket);
    }
}