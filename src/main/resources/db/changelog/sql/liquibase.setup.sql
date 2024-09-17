CREATE TABLE users
(
    "id"               BIGINT           NOT NULL    UNIQUE,
    "first_name"       VARCHAR(255)     NULL,
    "last_name"        VARCHAR(255)     NULL,
    "email"            VARCHAR(255)     NOT NULL    UNIQUE,
    "username"         VARCHAR(255)     NOT NULL    UNIQUE,
    "password"         VARCHAR(40)      NOT NULL,
    "version"          INTEGER          NOT NULL,
    "is_active"        BOOLEAN          NOT NULL,
    "created_at"       timestamp        NOT NULL,
    "modified_at"      timestamp        NOT NULL
);

CREATE SEQUENCE USERS_SEQ
    MINVALUE 1
    MAXVALUE 9999999999
    START WITH 1
    INCREMENT BY 1;

ALTER TABLE users
    ADD CONSTRAINT "PK_USERS"
        PRIMARY KEY (id);