package com.gameup.ticket_service.dto;

import lombok.Data;

@Data
public class AdminResponseDTO {
    private Long idAdmin;
    private Long idUsuario;
    private String nivelAcceso;
    private Boolean activo;
}