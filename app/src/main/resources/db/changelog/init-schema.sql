-- liquibase formatted sql
--changeset bishoy:1

CREATE TABLE PERSON
(
    NATIONAL_ID   TEXT,
    NAME          TEXT,
    AGE           TEXT,
    EMAIL_ADDRESS TEXT,
    MOBILE_NUMBER TEXT
);