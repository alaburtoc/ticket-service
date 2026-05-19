CREATE TABLE ticket_soporte (
                                id_ticket BIGINT AUTO_INCREMENT PRIMARY KEY,
                                id_usuario BIGINT NOT NULL,
                                asunto VARCHAR(255) NOT NULL,
                                descripcion TEXT NOT NULL,
                                estado VARCHAR(50) NOT NULL,
                                fecha_creacion DATE NOT NULL
);