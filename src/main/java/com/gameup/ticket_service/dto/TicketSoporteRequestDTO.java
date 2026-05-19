package com.gameup.ticket_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketSoporteRequestDTO {

    @NotNull(message = "El id de usuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "El id del admin es obligatorio")
    private Long idAdmin;

    @NotBlank(message = "El asunto es obligatorio")
    private String asunto;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
}