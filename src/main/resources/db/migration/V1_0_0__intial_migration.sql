CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE student (
    id uuid PRIMARY KEY,
    name VARCHAR(255)
);