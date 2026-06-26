package com.gameup.ticket_service.assembler;

import com.gameup.ticket_service.controller.TicketSoporteController;
import com.gameup.ticket_service.dto.TicketResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TicketModelAssembler implements RepresentationModelAssembler<TicketResponseDTO, EntityModel<TicketResponseDTO>> {

    @Override
    public EntityModel<TicketResponseDTO> toModel(TicketResponseDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(TicketSoporteController.class).obtenerPorId(dto.getIdTicket())).withSelfRel(),
                Link.of("/api/tickets").withRel("tickets"),
                Link.of("/api/tickets/usuario/" + dto.getIdUsuario()).withRel("tickets-usuario")
        );
    }
}