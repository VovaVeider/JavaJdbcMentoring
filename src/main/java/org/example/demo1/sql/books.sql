CREATE TABLE BOOKS
(
    id serial PRIMARY KEY,
    isbn varchar UNIQUE NOT NULL,
    title varchar NOT NULL,
    author varchar,
    published_date date
);
