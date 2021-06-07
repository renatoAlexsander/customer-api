DROP TABLE IF EXISTS CUSTOMERS;

CREATE TABLE CUSTOMERS (
    ID SERIAL PRIMARY KEY,
    BIRTH_DATE DATE NOT NULL,
    DOCUMENT VARCHAR(255) NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    REGISTER_AT TIMESTAMP(6) NOT NULL,
    UPDATED_AT TIMESTAMP(6) NOT NULL
);

CREATE SEQUENCE SEQ_CUSTOMERS START 1;

INSERT INTO CUSTOMERS (ID, BIRTH_DATE, DOCUMENT, NAME, REGISTER_AT, UPDATED_AT)
    VALUES ((SELECT NEXTVAL('SEQ_CUSTOMERS')), '1996-03-04', '33322211199', 'RENATO ALEXSANDER', CURRENT_DATE, CURRENT_DATE);

INSERT INTO CUSTOMERS (ID, BIRTH_DATE, DOCUMENT, NAME, REGISTER_AT, UPDATED_AT)
    VALUES ((SELECT NEXTVAL('SEQ_CUSTOMERS')), '1994-01-07', '44422200099', 'ALBERTO', CURRENT_DATE, CURRENT_DATE);

INSERT INTO CUSTOMERS (ID, BIRTH_DATE, DOCUMENT, NAME, REGISTER_AT, UPDATED_AT)
    VALUES ((SELECT NEXTVAL('SEQ_CUSTOMERS')), '2000-01-04', '00044411177', 'JOANA', CURRENT_DATE, CURRENT_DATE);
