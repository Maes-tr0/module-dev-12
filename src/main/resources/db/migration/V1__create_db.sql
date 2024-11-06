CREATE SEQUENCE IF NOT EXISTS seq_client_id
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS seq_ticket_id
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS clients
(
    id   BIGINT PRIMARY KEY DEFAULT nextval('seq_client_id'),
    name VARCHAR(200) NOT NULL CHECK (LENGTH(name) BETWEEN 3 AND 200)
);

CREATE TABLE IF NOT EXISTS planets
(
    id   VARCHAR(50) PRIMARY KEY CHECK (id ~ '^[A-Z0-9]+$'),
    name VARCHAR(500) NOT NULL CHECK (LENGTH(name) BETWEEN 1 AND 500)
);

CREATE TABLE IF NOT EXISTS tickets
(
    id             BIGINT PRIMARY KEY DEFAULT nextval('seq_ticket_id'),
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    client_id      BIGINT      NOT NULL,
    from_planet_id VARCHAR(50) NOT NULL,
    to_planet_id   VARCHAR(50) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (from_planet_id) REFERENCES planets (id),
    FOREIGN KEY (to_planet_id) REFERENCES planets (id)
);
