--CREATE TABLE USERS(
--    id int not null PRIMARY KEY ,
--    username varchar(45) not null,
--    password varchar(64) not null,
--    enabled boolean not null,
--    role varchar(45) not null
--);
--
--CREATE TABLE ITEMS(
--    id int PRIMARY KEY,
--    name varchar(128),
--    price int
--);

INSERT INTO USERS(id, username, password, enabled, role) VALUES (1, 'nirish', 'test', true, 'ROLE_ADMIN');
INSERT INTO USERS(id, username, password, enabled, role) VALUES (2, 'sai', 'test', true, 'ROLE_USER');
INSERT INTO USERS(id, username, password, enabled, role) VALUES (3, 'test', 'test', true, 'ROLE_USER');
INSERT INTO USERS(id, username, password, enabled, role)VALUES (4, 'admin', 'admin', true, 'ROLE_ADMIN');

INSERT INTO ITEMS VALUES (1, 'Chips', 10);
INSERT INTO ITEMS VALUES (2, 'Hide & Seek Biscuit', 35);
INSERT INTO ITEMS VALUES (3, 'Apple', 50);
INSERT INTO ITEMS VALUES (4, 'Banana', 5);
INSERT INTO ITEMS VALUES (5, 'lolipop', 2);
INSERT INTO ITEMS VALUES (6, 'icecream', 30);
INSERT INTO ITEMS VALUES (7, 'Biryani', 180);
