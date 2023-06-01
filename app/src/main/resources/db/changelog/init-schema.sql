-- liquibase formatted sql
--changeset bishoy:1

CREATE TABLE SYSTEM_USER
(
    NATIONAL_ID   TEXT PRIMARY KEY ,
    FIRST_NAME          TEXT,
    LAST_NAME          TEXT,
    AGE           TEXT,
    EMAIL_ADDRESS TEXT,
    MOBILE_NUMBER TEXT
);