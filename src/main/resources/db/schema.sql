CREATE TABLE IF NOT EXISTS  _user (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    is_active BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS  operation_log (
    id SERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    datetime TIMESTAMP NOT NULL,
    number1 BIGINT NOT NULL,
    number2 BIGINT NOT NULL,
    result BIGINT NOT NULL
);