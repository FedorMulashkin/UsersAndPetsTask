DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS pets;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE users(
    userId SERIAL PRIMARY KEY,
    username VARCHAR(25),
    password text,
    enabled BOOLEAN
);

CREATE TABLE roles(
    roleId SERIAL PRIMARY KEY,
    roleName VARCHAR(25)
);

CREATE TABLE users_roles(
    userId INT NOT NULL,
    roleId INT NOT NULL,
    PRIMARY KEY(userId, roleId),
    FOREIGN KEY (userId) REFERENCES users(userId),
    FOREIGN KEY (roleId) REFERENCES roles(roleId)
);

CREATE TABLE pets(
    petId SERIAL,
    name VARCHAR(25),
    kind VARCHAR(10),
    birthday DATE,
    sex CHAR(1),
    userId INT REFERENCES users(userId)
);

INSERT INTO roles VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO users(username, password, enabled) VALUES
    ('Arthas', '$2a$10$x5hchGXeTn1fMiTDYvUOqeyfvkBMtifUQ9JLX28K9WmP7BmjQFTAm', true),
    ('Thrall', '$2a$10$x5hchGXeTn1fMiTDYvUOqeyfvkBMtifUQ9JLX28K9WmP7BmjQFTAm', true),
    ('Silvana', '$2a$10$x5hchGXeTn1fMiTDYvUOqeyfvkBMtifUQ9JLX28K9WmP7BmjQFTAm', true);

INSERT INTO users_roles VALUES
    (1, 1),
    (2, 1),
    (3, 1);

INSERT INTO pets(name, kind, birthday, sex, userId) VALUES
('Mr. Bigglesworth', 'cat', '1997-12-24', 'm', 1),
('Invincible', 'dog', '2000-11-23', 'm', 1),
('Alleria', 'cat', '1990-02-02', 'f', 3),
('Verisa', 'cat', '1995-08-08', 'f', 3),
('Snow Song', 'dog', '2020-09-01', 'f', 2);
