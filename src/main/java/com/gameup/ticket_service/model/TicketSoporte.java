package com.gameup.ticket_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "ticket_soporte")
@Data
@NoArgsConstructor
public class TicketSoporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;

    private Long idUsuario;

    private Long idAdmin;

    private String asunto;

    private String descripcion;

    private String estado;

    private LocalDate fechaCreacion;
}