-- Jashakan Shanmugathasan
-- February 10, 2023
-- WEBD4201 Assigment 2
-- Creates database for any user

CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users(
    id BIGINT PRIMARY KEY,
    password VARCHAR(40) NOT NULL,
    firstName VARCHAR(128) NOT NULL ,
    lastName VARCHAR(128) NOT NULL ,
    emailAddress VARCHAR(255) NOT NULL ,
    lastAccess TIMESTAMP NOT NULL ,
    enrollDate TIMESTAMP NOT NULL ,
    enabled BOOLEAN,
    type VARCHAR(1)
);

ALTER TABLE users OWNER TO webd4201_admin;

INSERT INTO users(id, password, firstName, lastName, emailAddress, lastAccess, enrollDate, enabled, type)
VALUES (100111111,
        ENCODE(DIGEST('password','sha1'), 'hex'),
        'Mike',
        'Jones',
        'mike.jones@dcmail.ca',
        '2023-02-10',
        '2015-09-11',
        true,
        's'
        );

INSERT INTO users(id, password, firstName, lastName, emailAddress, lastAccess, enrollDate, enabled, type)
VALUES (
        10077806,
        ENCODE(DIGEST('jaashee','sha1'), 'hex'),
        'Jashakan',
        'Shanmugathasan',
        'jashakan.shanmugathasan@dcmail.ca',
        '2023-02-10',
        '2019-01-04',
        true,
        's'
        );

INSERT INTO users(id, password, firstName, lastName, emailAddress, lastAccess, enrollDate, enabled, type)
VALUES (100112233,
        ENCODE(DIGEST('password2', 'sha1'), 'hex'),
        'John',
        'Doe',
        'john.doe@dcmail.ca',
        '2019-04-30',
        '2016-09-02',
        true,
        's'
       );
INSERT INTO users(id, password, firstName, lastName, emailAddress, lastAccess, enrollDate, enabled, type)
VALUES (100223344,
        ENCODE(DIGEST('password3', 'sha1'), 'hex'),
        'alaadin',
        'addas',
        'alaadin.addas@dcmail.ca',
        '2022-04-30',
        '2014-09-06',
        true,
        'f'
       );
INSERT INTO users(id, password, firstName, lastName, emailAddress, lastAccess, enrollDate, enabled, type)
VALUES (100334455,
        ENCODE(DIGEST('password4', 'sha1'), 'hex'),
        'Clint',
        'Macdonald',
        'clint.macdonald@dcmail.ca',
        '2023-02-09',
        '2016-08-02',
        true,
        'f'
       );
INSERT INTO users(id, password, firstName, lastName, emailAddress, lastAccess, enrollDate, enabled, type)
VALUES (100445566,
        ENCODE(DIGEST('password5', 'sha1'), 'hex'),
        'Stephen',
        'Forbes',
        'stephen.forbes@dcmail.ca',
        '2023-02-08',
        '2013-09-02',
        true,
        'f'
       );

SELECT * FROM users;

