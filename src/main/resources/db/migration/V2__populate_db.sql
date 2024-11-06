INSERT INTO clients (id, name)
VALUES (1, 'John Doe'),
       (2, 'Jane Smith'),
       (3, 'Bob Johnson'),
       (4, 'Alice Williams'),
       (5, 'Michael Brown'),
       (6, 'Emily Davis'),
       (7, 'Daniel Wilson'),
       (8, 'Sophia Taylor'),
       (9, 'David Anderson'),
       (10, 'Olivia Thomas');

INSERT INTO planets (id, name)
VALUES ('EARTH', 'Earth'),
       ('MARS', 'Mars'),
       ('VENUS', 'Venus'),
       ('JUPITER', 'Jupiter'),
       ('SATURN', 'Saturn');

-- Оновлення послідовності для клієнтів
SELECT setval('seq_client_id', (SELECT MAX(id) FROM clients));

-- Оновлення послідовності для квитків
SELECT setval('seq_ticket_id', (SELECT MAX(id) FROM tickets));

