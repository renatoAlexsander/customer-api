INSERT INTO CUSTOMERS (ID, BIRTH_DATE, DOCUMENT, NAME, REGISTER_AT, UPDATED_AT)
    VALUES ((SELECT NEXTVAL('SEQ_CUSTOMERS')), '1996-03-04', '33322211199', 'JOAO GOMES', CURRENT_DATE, CURRENT_DATE);