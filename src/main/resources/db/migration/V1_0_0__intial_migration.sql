CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE product (
    id uuid PRIMARY KEY,
    name VARCHAR(255),
    price numeric,
    date_created timestamp,
    date_updated timestamp
);