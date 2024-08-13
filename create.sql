DROP TABLE ODONTOLOGOS IF EXISTS;

CREATE TABLE ODONTOLOGOS(
ID INT AUTO_INCREMENT PRIMARY KEY,
NROMATRICULA INT NOT NULL,
NOMBRE VARCHAR(25),
APELLIDO VARCHAR(25)
);

INSERT INTO ODONTOLOGOS VALUES(DEFAULT, 234, 'MARTIN', 'GONZALEZ'),
(DEFAULT, 134, 'MALENA', 'VALDEZ'),
(DEFAULT, 214, 'MARCO', 'LORENZO'),
(DEFAULT, 315, 'JUAN','PEREZ'),
(DEFAULT, 268, 'MARTINA','LOPEZ');

