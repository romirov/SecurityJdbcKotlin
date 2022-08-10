CREATE TABLE IF NOT EXISTS users (
    id SERIAL UNIQUE,
    fistname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
    password varchar(100) NOT NULL,
     PRIMARY KEY(id, username)--,
--     UNIQUE(id, username)
);

CREATE TABLE IF NOT EXISTS roles (
   id SERIAL PRIMARY KEY UNIQUE,
   name VARCHAR(100) NOT NULL--,
   --UNIQUE(id, role)
);

CREATE TABLE IF NOT EXISTS users_roles (
   id SERIAL PRIMARY KEY UNIQUE,
   user_id INTEGER,
   role_id INTEGER
--    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
--    FOREIGN KEY (role_id) REFERENCES roles(id)--,
   --UNIQUE(id)
);

INSERT INTO users VALUES(1, 'Admin', 'Admin', 'admin', 'admin@test.com', '$2a$12$8W44dpkSpB2seHviwMKoI.ohKj9FbYyNR3MAOO6G.tiODxrF3Cszu');
INSERT INTO users VALUES(2, 'User', 'User', 'user', 'user@test.com', '$2a$12$RfUSHgmB6kERRBGBzZKgv.hZJjQAX.zu8EaUeCLXXLpjETLW1HQxm');


-- INSERT INTO roles VALUES(1, 'ADMIN');
-- INSERT INTO roles VALUES(2, 'USER');
--
-- INSERT INTO users_roles VALUES(1, 1, 1);
-- INSERT INTO users_roles VALUES(2, 1, 2);
-- INSERT INTO users_roles VALUES(3, 2, 2);
