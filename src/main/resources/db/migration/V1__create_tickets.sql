CREATE TABLE tickets (

                         id_ticket BIGINT PRIMARY KEY AUTO_INCREMENT,
                         id_usuario BIGINT NOT NULL,
                         asunto VARCHAR(255) NOT NULL,
                         descripcion VARCHAR(500) NOT NULL,
                         estado VARCHAR(100) NOT NULL,
                         fecha_creacion DATE NOT NULL

);