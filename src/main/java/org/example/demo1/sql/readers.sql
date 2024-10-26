
CREATE TABLE READERS
(
    id serial PRIMARY KEY,
    name varchar NOT NULL,
    email varchar UNIQUE NOT NULL
);
