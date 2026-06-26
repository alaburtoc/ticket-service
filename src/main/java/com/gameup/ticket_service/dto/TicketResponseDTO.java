package com.gameup.ticket_service.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDTO {
    private Long idTicket;
    private Long idUsuario;
    private String asunto;
    private String descripcion;
    private String estado;
    private LocalDate fechaCreacion;
}