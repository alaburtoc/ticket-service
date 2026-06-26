package com.gameup.ticket_service.config;

import com.gameup.ticket_service.model.TicketSoporte;
import com.gameup.ticket_service.repository.TicketSoporteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TicketSoporteRepository repository;

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            log.info(">>> Tickets ya cargados. Se omite la inicialización.");
            return;
        }

        Faker faker = new Faker();
        String[] estados = {"ABIERTO", "PENDIENTE", "CERRADO"};

        for (int i = 0; i < 10; i++) {
            TicketSoporte ticket = TicketSoporte.builder()
                    .idUsuario((long) faker.number().numberBetween(1, 6))
                    .asunto(faker.lorem().sentence(4))
                    .descripcion(faker.lorem().sentence(10))
                    .estado(estados[faker.number().numberBetween(0, estados.length)])
                    .fechaCreacion(LocalDate.now().minusDays(faker.number().numberBetween(1, 90)))
                    .build();
            repository.save(ticket);
        }

        log.info(">>> 10 tickets generados con DataFaker OK.");
    }
}