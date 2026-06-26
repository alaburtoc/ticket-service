package com.gameup.ticket_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestDTO {

    @NotNull(message = "El id de usuario es obligatorio")
    private Long idUsuario;

    @NotBlank(message = "El asunto no puede estar vacío")
    @Size(max = 255, message = "El asunto no puede superar 255 caracteres")
    private String asunto;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 500, message = "La descripción no puede superar 500 caracteres")
    private String descripcion;

    private String estado;
    private LocalDate fechaCreacion;
}