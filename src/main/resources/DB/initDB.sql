DROP TABLE IF EXISTS users;
CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    chat_id    INTEGER UNIQUE                NOT NULL,
    name       VARCHAR                       NOT NULL,
    phone_num  VARCHAR                       NOT NULL,
    bot_specs  VARCHAR                       NOT NULL,
    bot_state  VARCHAR                       NOT NULL
);

