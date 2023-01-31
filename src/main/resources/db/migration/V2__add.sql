INSERT INTO users(id, archived, email, name, password, role)
VALUES (1, false, 'mmm.mail', 'admin', 'pass', 'ADMIN');


ALTER SEQUENCE user_seq RESTART WITH 2;
