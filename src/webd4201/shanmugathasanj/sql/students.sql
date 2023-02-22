-- Jashakan Shanmugathasan
-- February 10, 2023
-- WEBD4201 Assigment 2
-- Creates table for students

CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP TABLE IF EXISTS students CASCADE;

CREATE TABLE students(
    id BIGINT PRIMARY KEY,
    programCode VARCHAR(4),
    programDescription VARCHAR(255),
    year INT,
    FOREIGN KEY (id) REFERENCES users(id)
);

INSERT INTO students (id, programCode, programDescription, year)
VALUES (100111111, 'CSTY', 'Computer Systems  Technology',3);

INSERT INTO students (id, programCode, programDescription, year)
VALUES (10077806, 'CPA', 'Computer Programming & Analysis',2);

INSERT INTO students (id, programCode, programDescription, year)
VALUES (100112233, 'CPA', 'Computer Programming & Analysis',3);

