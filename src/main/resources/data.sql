INSERT INTO client (id, tax_number, first_name, surname, client_status) VALUES (1, '93434299194', 'Marko', 'Markić', 'ACTIVE');
INSERT INTO client (id, tax_number, first_name, surname, client_status) VALUES (2, '93434299195', 'Pero', 'Perić', 'ACTIVE');
INSERT INTO client (id, tax_number, first_name, surname, client_status) VALUES (3, '93434299196', 'Ivica', 'Ivić', 'ACTIVE');
INSERT INTO client (id, tax_number, first_name, surname, client_status) VALUES (4, '93434299197', 'Hrvoje', 'Horvat', 'INACTIVE');


insert into card_request(id, card_request_status, created, id_client) values (1, 'PENDING_PROCESSING', '2024-04-17 17:05', 1);
insert into card_request(id, card_request_status, created, updated, id_client) values (2, 'IN_PROGRESS', '2024-04-17 17:06', '2024-04-17 17:06', 2);
insert into card_request(id, card_request_status, created, updated, id_client) values (3, 'APPROVED', '2024-04-17 18:05', '2024-04-17 18:05', 3);