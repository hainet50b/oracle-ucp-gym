CREATE TABLE emp
(
    id   NUMBER GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk1 PRIMARY KEY (id)
);
