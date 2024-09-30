CREATE TABLE books
(
    id               SERIAL PRIMARY KEY,
    title            VARCHAR(255) NOT NULL,
    author           VARCHAR(255) NOT NULL,
    publication_year INT          NOT NULL
);

INSERT INTO books (title, author, publication_year) VALUES ('Test Book 1', 'Author 1', 2021);
INSERT INTO books (title, author, publication_year) VALUES ('Test Book 2', 'Author 1', 2022);
INSERT INTO books (title, author, publication_year) VALUES ('Test Book 3', 'Author 2', 2020);
INSERT INTO books (title, author, publication_year) VALUES ('Test Book 4', 'Author 2', 2024);
INSERT INTO books (title, author, publication_year) VALUES ('Test Book 5', 'Author 2', 2019);