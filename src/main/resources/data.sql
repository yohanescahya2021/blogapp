INSERT INTO users (name, username, email, password) VALUES ('admin', 'admin', 'admin@gmail.com', '$2a$10$GAiLPu7nTYgeDGohtRQ3uOrhL.5CxJssxN4nlEpcvHGj6D7E.m3m6');
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);