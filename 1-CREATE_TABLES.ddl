-- Generated by Oracle SQL Developer Data Modeler 19.2.0.182.1216
--   at:        2020-02-28 07:50:27 CST
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g



CREATE TABLE author (
    author_id     NUMBER(20) NOT NULL,
    author_name   VARCHAR2(250) NOT NULL
);

ALTER TABLE author ADD CONSTRAINT author_pk PRIMARY KEY ( author_id );

CREATE TABLE book (
    isbn_no                 NUMBER(20) NOT NULL,
    copy_no                 NUMBER(20) NOT NULL,
    shelf_id                NUMBER(20) NOT NULL,
    book_title              VARCHAR2(120) NOT NULL,
    current_status          VARCHAR2(120) NOT NULL,
    loan_hold_status_date   DATE
);

ALTER TABLE book ADD CONSTRAINT book_pk PRIMARY KEY ( isbn_no,
                                                      copy_no );

CREATE TABLE users (
    loginid       NUMBER(10) NOT NULL,
    password      VARCHAR2(20) NOT NULL,
    last_name     VARCHAR2(120) NOT NULL,
    first_name    VARCHAR2(120) NOT NULL,
    middle_name   VARCHAR2(120),
    user_type     VARCHAR2(9) NOT NULL
);

ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( loginid );
CREATE TABLE librarian (
    loginid NUMBER(10) NOT NULL
);

ALTER TABLE librarian ADD CONSTRAINT librarian_pk PRIMARY KEY ( loginid );

CREATE TABLE patron (
    patron_no      NUMBER(6) NOT NULL,
    loginid        NUMBER(10) NOT NULL,
    unpaid_fines   NUMBER(8)
);

ALTER TABLE patron ADD CONSTRAINT patron_pk PRIMARY KEY ( patron_no );

CREATE TABLE shelf (
    shelf_id     NUMBER(20) NOT NULL,
    shelf_name   VARCHAR2(120) NOT NULL,
    capacity     NUMBER(8)
);

ALTER TABLE shelf ADD CONSTRAINT shelf_pk PRIMARY KEY ( shelf_id );

CREATE TABLE transaction (
    accession_no           NUMBER(8) NOT NULL,
    isbn_no                NUMBER(20) NOT NULL,
    copy_no                NUMBER(20) NOT NULL,
    patron_no              NUMBER(6) NOT NULL,
    book_title             VARCHAR2(120) NOT NULL,
    status                 VARCHAR2(120) NOT NULL,
    reservation_date       DATE,
    reservation_date_exp   DATE,
    date_borrowed          DATE,
    due_date               DATE,
    date_returned          DATE,
    penalty_charge         NUMBER(8, 2),
    fine_status            VARCHAR2(120)
);

ALTER TABLE transaction
    ADD CONSTRAINT transaction_pk PRIMARY KEY ( accession_no,
                                                patron_no,
                                                isbn_no,
                                                copy_no );


CREATE TABLE written_work (
    isbn_no                NUMBER(20) NOT NULL,
    copy_no                NUMBER(20) NOT NULL,
    author_id              NUMBER(20) NOT NULL,
    year_of_publication    NUMBER(4) NOT NULL,
    place_of_publication   VARCHAR2(250)
);

ALTER TABLE written_work
    ADD CONSTRAINT written_work_pk PRIMARY KEY ( isbn_no,
                                                 author_id,
                                                 copy_no );

ALTER TABLE book
    ADD CONSTRAINT book_shelf_fk FOREIGN KEY ( shelf_id )
        REFERENCES shelf ( shelf_id );

ALTER TABLE librarian
    ADD CONSTRAINT librarian_users_fk FOREIGN KEY ( loginid )
        REFERENCES users ( loginid );

ALTER TABLE patron
    ADD CONSTRAINT patron_users_fk FOREIGN KEY ( loginid )
        REFERENCES users ( loginid );

ALTER TABLE transaction
    ADD CONSTRAINT transaction_book_fk FOREIGN KEY ( isbn_no,
                                                     copy_no )
        REFERENCES book ( isbn_no,
                          copy_no );

ALTER TABLE transaction
    ADD CONSTRAINT transaction_patron_fk FOREIGN KEY ( patron_no )
        REFERENCES patron ( patron_no );

ALTER TABLE written_work
    ADD CONSTRAINT written_work_author_fk FOREIGN KEY ( author_id )
        REFERENCES author ( author_id );

ALTER TABLE written_work
    ADD CONSTRAINT written_work_book_fk FOREIGN KEY ( isbn_no,
                                                      copy_no )
        REFERENCES book ( isbn_no,
                          copy_no );
/

CREATE SEQUENCE authorid_seq
INCREMENT BY 150
START WITH 800000
NOMINVALUE
NOMAXVALUE
NOCACHE
NOCYCLE;
/
CREATE SEQUENCE accession_no_seq
INCREMENT BY 100
START WITH 2000000
NOMINVALUE
NOMAXVALUE
NOCACHE
NOCYCLE;
/
CREATE SEQUENCE patron_no_seq
INCREMENT BY 10
START WITH 10000
NOMINVALUE
NOMAXVALUE
NOCACHE
NOCYCLE;
--  ERROR: No Discriminator Column found in Arc FKArc_2 - constraint trigger for Arc cannot be generated 

--  ERROR: No Discriminator Column found in Arc FKArc_2 - constraint trigger for Arc cannot be generated



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             8
-- CREATE INDEX                             0
-- ALTER TABLE                             16
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   2
-- WARNINGS                                 0
