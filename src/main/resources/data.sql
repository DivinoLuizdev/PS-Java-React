CREATE TABLE conta
(
    id_conta IDENTITY NOT NULL PRIMARY KEY,
    nome_responsavel VARCHAR(50) NOT NULL
);


CREATE TABLE transferencia
(
    id IDENTITY NOT NULL PRIMARY KEY,
    data_transferencia TIMESTAMP WITH TIME ZONE NOT NULL,
    valor NUMERIC (20,2) NOT NULL,
    tipo VARCHAR(15) NOT NULL,
    nome_operador_transacao VARCHAR (50),
    conta_id INT NOT NULL,

        CONSTRAINT FK_CONTA
        FOREIGN KEY (conta_id)
        REFERENCES conta(id_conta)
);

INSERT INTO conta (id_conta, nome_responsavel) VALUES (1,'Fulano');
INSERT INTO conta (id_conta, nome_responsavel) VALUES (2,'Sicrano');

INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (1,'2019-01-01 12:00:00+03',30895.46,'DEPOSITO', null, 1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (2,'2019-02-03 09:53:27+03',12.24,'DEPOSITO', null,2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (3,'2019-05-04 08:12:45+03',-500.50,'SAQUE', null,1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (4,'2019-08-07 08:12:45+03',-530.50,'SAQUE', null,2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (5,'2020-06-08 10:15:01+03',3241.23,'TRANSFERENCIA', 'Beltrano',1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (6,'2021-04-01 12:12:04+03',25173.09,'TRANSFERENCIA', 'Ronnyscley',2);


-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (1, '2019-01-01 12:00:00+03', 30895.46, 'DEPOSITO', 'Operador1', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (2, '2019-02-03 09:53:27+03', 12.24, 'DEPOSITO', 'Operador2', 2);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (3, '2019-05-04 08:12:45+03', -500.50, 'SAQUE', 'Operador1', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (4, '2019-08-07 08:12:45+03', -530.50, 'SAQUE', 'Operador2', 2);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (5, '2020-06-08 10:15:01+03', 3241.23, 'TRANSFERENCIA', 'Beltrano', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (6, '2021-04-01 12:12:04+03', 25173.09, 'TRANSFERENCIA', 'Ronnyscley', 2);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (7, '2019-03-15 14:20:10+03', 100.00, 'DEPOSITO', 'Operador3', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (8, '2020-11-20 11:05:30+03', -50.75, 'SAQUE', 'Operador1', 2);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (9, '2021-07-10 09:30:15+03', 500.00, 'TRANSFERENCIA', 'Beltrano', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (10, '2022-02-28 16:40:22+03', -200.25, 'SAQUE', 'Operador2', 2);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (11, '2020-04-12 08:50:18+03', 1000.00, 'DEPOSITO', 'Operador3', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (12, '2021-10-05 13:25:45+03', -300.50, 'SAQUE', 'Operador1', 2);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (13, '2019-07-15 17:35:11+03', 2500.75, 'TRANSFERENCIA', 'Beltrano', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (14, '2020-05-28 14:55:29+03', -150.00, 'SAQUE', 'Operador2', 2);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (15, '2022-01-10 11:30:40+03', 200.00, 'DEPOSITO', 'Operador3', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (16, '2022-06-03 09:15:12+03', -400.25, 'SAQUE', 'Operador1', 2);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (17, '2021-09-20 16:20:08+03', 1500.50, 'TRANSFERENCIA', 'Beltrano', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (18, '2022-03-10 12:40:35+03', -250.75, 'SAQUE', 'Operador2', 2);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (19, '2020-08-12 15:55:55+03', 3000.00, 'DEPOSITO', 'Operador3', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (20, '2021-12-05 11:20:22+03', -500.50, 'SAQUE', 'Operador1', 2);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (21, '2022-05-15 10:35:15+03', 500.75, 'TRANSFERENCIA', 'Beltrano', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (22, '2023-02-28 14:50:30+03', -100.25, 'SAQUE', 'Operador2', 2);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (23, '2021-03-12 09:10:12+03', 10000.00, 'DEPOSITO', 'Operador3', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (24, '2022-09-05 12:35:45+03', -2000.50, 'SAQUE', 'Operador1', 2);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (25, '2022-07-15 18:50:55+03', 5000.75, 'TRANSFERENCIA', 'Beltrano', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (26, '2023-01-10 15:10:20+03', -500.75, 'SAQUE', 'Operador2', 2);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (27, '2021-04-20 11:30:15+03', 1000.00, 'DEPOSITO', 'Operador3', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (28, '2022-10-05 14:45:35+03', -1500.50, 'SAQUE', 'Operador1', 2);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (29, '2023-07-20 09:55:40+03', 2000.75, 'TRANSFERENCIA', 'Beltrano', 1);
-- INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (30, '2023-06-15 10:30:55+03', -200.75, 'SAQUE', 'Operador2', 2);
