INSERT INTO users(id, archived, email, name, password, role, bucket_id)
VALUES (1, false, 'mmm.mail', 'admin', 'pass', 'ADMIN', null);


ALTER SEQUENCE user_seq RESTART WITH 2;