package com.gameup.ticket_service.repository;

import com.gameup.ticket_service.model.TicketSoporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketSoporteRepository extends JpaRepository<TicketSoporte, Long> {
    List<TicketSoporte> findByIdUsuario(Long idUsuario);
    List<TicketSoporte> findByEstado(String estado);
}