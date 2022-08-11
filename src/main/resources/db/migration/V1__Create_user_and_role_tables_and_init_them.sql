CREATE TABLE IF NOT EXISTS users (
    id SERIAL UNIQUE,
    fistname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
    password varchar(100) NOT NULL,
     PRIMARY KEY(id, username)
);

CREATE TABLE IF NOT EXISTS roles (
   id SERIAL PRIMARY KEY UNIQUE,
   name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles (
   id SERIAL PRIMARY KEY UNIQUE,
   users_id INTEGER,
   roles_id INTEGER,
   FOREIGN KEY (users_id) REFERENCES users(id) ON DELETE CASCADE,
   FOREIGN KEY (roles_id) REFERENCES roles(id) ON DELETE CASCADE
);



INSERT INTO users VALUES(1, 'Admin', 'Admin', 'admin', 'admin@test.com', '$2a$12$8W44dpkSpB2seHviwMKoI.ohKj9FbYyNR3MAOO6G.tiODxrF3Cszu');
-- INSERT INTO users VALUES(2, 'User', 'User', 'user', 'user@test.com', '$2a$12$RfUSHgmB6kERRBGBzZKgv.hZJjQAX.zu8EaUeCLXXLpjETLW1HQxm');

INSERT INTO roles VALUES (1, 'ADMIN'), (2, 'USER');

INSERT INTO users_roles VALUES (1, 1, 1);