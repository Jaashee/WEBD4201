-- Jashakan Shanmugathasan
-- February 10, 2023
-- WEBD4201 Assigment 2
-- Creates table for faculty

CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP TABLE IF EXISTS faculty CASCADE;

CREATE TABLE faculty(
    id BIGINT PRIMARY KEY,
    schoolCode VARCHAR(5),
    schoolDescription VARCHAR(255),
    office VARCHAR (6),
    extension INT,
    FOREIGN KEY (ID) REFERENCES users(id)
);

INSERT INTO faculty (id, schoolCode, schoolDescription, office, extension)
VALUES (100223344,'SEIT','Faculty of Science,Engineering & Information Technology','C-111',1234);

INSERT INTO faculty (id, schoolCode, schoolDescription, office, extension)
VALUES (100334455,'SEIT','Faculty of Science,Engineering & Information Technology','C-315',2044);

INSERT INTO faculty (id, schoolCode, schoolDescription, office, extension)
VALUES (100445566,'SEIT','Faculty of Science,Engineering & Information Technology','b-110',4321);

SELECT * FROM faculty;