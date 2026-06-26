package com.gameup.ticket_service.service;

import com.gameup.ticket_service.dto.TicketRequestDTO;
import com.gameup.ticket_service.dto.TicketResponseDTO;
import com.gameup.ticket_service.exception.ResourceNotFoundException;
import com.gameup.ticket_service.model.TicketSoporte;
import com.gameup.ticket_service.repository.TicketSoporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests del TicketSoporteService")
class TicketSoporteServiceTest {

    @Mock private TicketSoporteRepository repository;

    @InjectMocks
    private TicketSoporteService service;

    private TicketSoporte ticketMock;

    @BeforeEach
    void setUp() {
        ticketMock = TicketSoporte.builder()
                .idTicket(1L).idUsuario(1L)
                .asunto("Problema con login")
                .descripcion("No puedo iniciar sesión")
                .estado("ABIERTO")
                .fechaCreacion(LocalDate.now())
                .build();
    }

    @Test
    @DisplayName("Obtener todos los tickets retorna lista")
    void obtenerTodos_retornaLista() {
        when(repository.findAll()).thenReturn(List.of(ticketMock));
        List<TicketResponseDTO> resultado = service.obtenerTodos();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getAsunto()).isEqualTo("Problema con login");
    }

    @Test
    @DisplayName("Obtener por ID existente retorna ticket")
    void obtenerPorId_existente_retornaTicket() {
        when(repository.findById(1L)).thenReturn(Optional.of(ticketMock));
        TicketResponseDTO resultado = service.obtenerPorId(1L);
        assertThat(resultado.getIdTicket()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Obtener por ID inexistente lanza excepción")
    void obtenerPorId_inexistente_lanzaExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.obtenerPorId(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Obtener tickets por usuario retorna lista")
    void obtenerPorUsuario_retornaLista() {
        when(repository.findByIdUsuario(1L)).thenReturn(List.of(ticketMock));
        List<TicketResponseDTO> resultado = service.obtenerPorUsuario(1L);
        assertThat(resultado).hasSize(1);
    }

    @Test
    @DisplayName("Crear ticket exitosamente")
    void guardar_exitoso() {
        TicketRequestDTO dto = TicketRequestDTO.builder()
                .idUsuario(1L).asunto("Nuevo problema")
                .descripcion("Descripcion del problema").build();
        when(repository.save(any(TicketSoporte.class))).thenReturn(ticketMock);
        TicketResponseDTO resultado = service.guardar(dto);
        assertThat(resultado).isNotNull();
        verify(repository).save(any(TicketSoporte.class));
    }

    @Test
    @DisplayName("Actualizar estado exitosamente")
    void actualizarEstado_exitoso() {
        when(repository.findById(1L)).thenReturn(Optional.of(ticketMock));
        when(repository.save(any(TicketSoporte.class))).thenReturn(ticketMock);
        TicketResponseDTO resultado = service.actualizarEstado(1L, "CERRADO");
        assertThat(resultado).isNotNull();
        verify(repository).save(any(TicketSoporte.class));
    }

    @Test
    @DisplayName("Eliminar ticket inexistente lanza excepción")
    void eliminar_inexistente_lanzaExcepcion() {
        when(repository.existsById(99L)).thenReturn(false);
        assertThatThrownBy(() -> service.eliminar(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}