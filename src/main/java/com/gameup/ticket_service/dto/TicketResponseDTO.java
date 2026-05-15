package com.gameup.ticket_service.dto;

import java.time.LocalDate;

public class TicketResponseDTO {

    private Long idTicket;
    private Long idUsuario;
    private String asunto;
    private String descripcion;
    private String estado;
    private LocalDate fechaCreacion;

    public TicketResponseDTO(Long idTicket,
                             Long idUsuario,
                             String asunto,
                             String descripcion,
                             String estado,
                             LocalDate fechaCreacion) {

        this.idTicket = idTicket;
        this.idUsuario = idUsuario;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
}
