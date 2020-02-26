create or replace procedure addauthor
(p_authorname IN varchar2)
as
begin
    insert into author
    values (authorid_seq.nextval, p_authorname);
end;
/
create or replace PROCEDURE addbook
(p_isbn_no IN NUMBER, p_book_title IN VARCHAR2,
p_copy_no IN NUMBER,
p_shelf_id IN NUMBER, p_status IN VARCHAR2)
IS
    v_authorid author.author_id%TYPE;
BEGIN 
    INSERT INTO book (isbn_no, book_title, copy_no, shelf_id, current_status)
    VALUES(p_isbn_no, p_book_title, p_copy_no, p_shelf_id, p_status);
END;
/
create or replace PROCEDURE addshelf
(p_shelfid IN NUMBER, p_shelf_name IN VARCHAR2,
p_capacity IN NUMBER)
IS
BEGIN 
    INSERT INTO shelf
    VALUES(p_shelfid, p_shelf_name, p_capacity);
END;
/
create or replace PROCEDURE addUser
(p_loginId IN NUMBER, p_password IN VARCHAR2,
p_lastName IN VARCHAR2, p_firstName IN VARCHAR2,
p_middleName IN VARCHAR2, p_userType IN VARCHAR2)
IS
BEGIN 
    INSERT INTO users 
    VALUES(p_loginid, p_password, p_lastName, p_firstname, p_middlename, p_usertype);

    CASE p_usertype
        WHEN 'Librarian' THEN
            INSERT INTO librarian
            VALUES(p_loginid);

            INSERT INTO patron (loginid, patron_no)
            VALUES(p_loginid, patron_no_seq.nextval);
        ELSE
            INSERT INTO patron (patron_no, loginid)
            VALUES(patron_no_seq.nextval, p_loginid);
    END CASE;
END;
/
create or replace PROCEDURE addwrittenwork
(p_isbn_no IN NUMBER, p_authorid IN NUMBER,
p_year IN NUMBER, p_place IN VARCHAR2)
IS
BEGIN 
    INSERT INTO written_work
    VALUES(p_isbn_no, p_authorid, p_year, p_place);
END;
/
create or replace procedure autoupdatetransaction
as 
    v_days NUMBER;
    v_unpaidfines patron.unpaid_fines%TYPE;
    CURSOR c_borrow_due_loginid IS
        SELECT accession_no, date_borrowed, due_date, patron.patron_no
        FROM transaction join patron
        on transaction.patron_no = patron.patron_no
        WHERE date_returned IS NULL;
begin
    for c in c_borrow_due_loginid LOOP
        v_days := sysdate - c.due_date;
        v_days := floor(v_days);
        dbms_output.put_line('Days: ' || v_days);
        if v_days >= 1 then
            update transaction
            set penalty_charge = v_days * 20,
                fine_status = 'UNPAID'
            where accession_no = c.accession_no;
        end if;
        update patron
        set unpaid_fines = (select SUM(penalty_charge) 
                            from transaction
                            where patron_no = c.patron_no AND fine_status = 'UNPAID')
        where patron_no = c.patron_no;                    
    end loop;
end;
/
create or replace PROCEDURE deletebook
(p_isbn IN NUMBER)
IS
BEGIN 
    DELETE FROM book
    WHERE isbn_no = p_isbn;
END;
/
create or replace PROCEDURE deleteUser
(p_loginId IN NUMBER, p_userType IN VARCHAR2)
IS
BEGIN 
    CASE p_usertype
        WHEN 'Patron' THEN
            DELETE FROM patron
            WHERE loginid = p_loginid;
        WHEN 'Librarian' THEN
            DELETE FROM librarian
            WHERE loginid = p_loginid;
    END CASE;  
    DELETE FROM users
    WHERE loginid = p_loginid;
END;
/
create or replace PROCEDURE editbook
(p_isbn IN NUMBER, p_booktitle IN VARCHAR2,
p_copyno IN NUMBER,
p_shelfid IN NUMBER)
IS
    v_booktitle book.book_title%TYPE := p_booktitle;
    v_copyno book.copy_no%TYPE := p_copyno;
    v_shelfid book.shelf_id%TYPE := p_shelfid;
BEGIN 
    IF v_booktitle IS NULL THEN
        SELECT book_title INTO v_booktitle FROM book WHERE isbn_no = p_isbn;
    END IF;
    IF v_copyno IS NULL THEN
        SELECT copy_no INTO v_copyno FROM book WHERE isbn_no = p_isbn;
    END IF;
    IF v_shelfid IS NULL THEN
        SELECT shelf_id INTO v_shelfid FROM book WHERE isbn_no = p_isbn;
    END IF;

    UPDATE book
    SET book_title = v_booktitle,
        copy_no = v_copyno,
        shelf_id = v_shelfid
    WHERE isbn_no = p_isbn;
END;
/
create or replace PROCEDURE editUser
(p_loginId IN NUMBER, p_password IN VARCHAR2,
p_lastName IN VARCHAR2, p_firstName IN VARCHAR2,
p_middleName IN VARCHAR2)
IS
    v_password users.password%TYPE;
    v_lastname users.last_name%TYPE;
    v_firstname users.first_name%TYPE;
    v_middle users.middle_name%TYPE;
BEGIN 
    v_password := p_password;
    v_lastname := p_lastname;
    v_firstname := p_firstname;
    v_middle := p_middlename;

    IF v_password IS NULL THEN
        SELECT password INTO v_password
        FROM users 
        WHERE loginid = p_loginid;
    END IF;
    IF v_lastname IS NULL THEN
        SELECT last_name INTO v_lastname
        FROM users 
        WHERE loginid = p_loginid;
    END IF;
    IF v_firstname IS NULL THEN
        SELECT first_name INTO v_firstname
        FROM users 
        WHERE loginid = p_loginid;
    END IF;
    IF v_middle IS NULL THEN
        SELECT middle_name INTO v_middle
        FROM users 
        WHERE loginid = p_loginid;
    END IF;

    UPDATE users
    SET password = v_password,
        last_name = v_lastname, 
        first_name = v_firstname, 
        middle_name = v_middle
    WHERE loginid = p_loginid;
END;
/
create or replace PROCEDURE returnbook 
(p_loginid IN VARCHAR2, p_isbn IN NUMBER,
p_status IN VARCHAR2, p_date_borrowed IN DATE,
p_date_returned IN DATE, p_finestatus IN VARCHAR2)
IS
    v_booktitle book.book_title%TYPE;   
    v_accession transaction.accession_no%TYPE;
    v_penalty transaction.penalty_charge%TYPE;
BEGIN 
    SELECT book_title INTO v_booktitle
    FROM book
    WHERE isbn_no = p_isbn;

    SELECT accession_no INTO v_accession
    FROM transaction
    WHERE isbn_no = p_isbn AND date_borrowed LIKE p_date_borrowed AND status = 'WITHDRAWN';

    SELECT penalty_charge INTO v_penalty
    FROM transaction
    WHERE accession_no = v_accession;

    UPDATE book
    SET current_status = 'STORED',
        loan_hold_status_date = NULL
    WHERE isbn_no = p_isbn;

    UPDATE transaction 
    SET status = p_status, date_returned = p_date_returned
    WHERE accession_no = v_accession;

    IF v_penalty IS NOT NULL OR v_penalty > 0 THEN
        UPDATE transaction
        SET fine_status = p_finestatus
        WHERE accession_no = v_accession;
    END IF;

    update patron
    set unpaid_fines = NULL
    where loginid = p_loginid;
END;
/
create or replace PROCEDURE withdrawbook -- LIBRARIAN ACCESS
(p_loginid IN NUMBER, p_isbn IN NUMBER,
p_date_borrowed IN DATE, p_status IN VARCHAR2,
p_reservedate IN DATE)
IS
    v_booktitle book.book_title%TYPE;
    v_patron_no patron.patron_no%TYPE;
    v_reservedate DATE;
BEGIN 
    v_reservedate := p_reservedate;
    
    SELECT book_title INTO v_booktitle
    FROM book
    WHERE isbn_no = p_isbn;

    SELECT patron_no INTO v_patron_no
    FROM patron
    WHERE loginid = p_loginid;
    
    UPDATE book
    SET current_status = 'WITHDRAWN',
        loan_hold_status_date = p_date_borrowed
    WHERE isbn_no = p_isbn;
    
    IF v_reservedate IS NOT NULL THEN 
        UPDATE transaction
        SET status = 'WITHDRAWN',
            date_borrowed = p_date_borrowed,
            due_date = p_date_borrowed + 7
        WHERE reservation_date LIKE p_reservedate AND book_title = v_booktitle AND isbn_no = p_isbn;
    ELSE 
        INSERT INTO transaction 
        (accession_no, patron_no, isbn_no, book_title, status, date_borrowed, due_date)
        VALUES(accession_no_seq.NEXTVAL, v_patron_no, p_isbn, v_booktitle, p_status,
            p_date_borrowed, p_date_borrowed + 7);
    END IF;        
END;
/
create or replace PROCEDURE retrieveTransactions
(p_patron_no IN patron.patron_no%TYPE,
c_cursor OUT SYS_REFCURSOR)
AS
BEGIN 
    OPEN c_cursor FOR
        SELECT *
        FROM transaction
        WHERE patron_no = p_patron_no;
END;
/
create or replace PROCEDURE retrieveBooks
(p_book_title IN book.book_title%TYPE,
 c_cursor OUT SYS_REFCURSOR)
is 
begin 
    OPEN c_cursor FOR
        select w.isbn_no, b.book_title, a.author_name, w.year_of_publication, w.place_of_publication, b.current_status
        from written_work w join book b on w.isbn_no = b.isbn_no
        join author a on w.author_id = a.author_id
        WHERE UPPER(b.book_title) like UPPER(p_book_title || '%');
end;
/
create or replace PROCEDURE reserve
(p_isbn IN book.isbn_no%TYPE, 
p_book_title IN book.book_title%TYPE,
p_patron_no IN patron.patron_no%TYPE)
IS
    v_status book.current_status%TYPE;
BEGIN   
    SELECT current_status INTO v_status FROM book
    WHERE isbn_no = p_isbn;

    IF v_status = 'STORED' THEN
        UPDATE book
        SET current_status = 'RESERVED', loan_hold_status_date = SYSDATE
        WHERE isbn_no = p_isbn;

        INSERT into TRANSACTION (accession_no, isbn_no, patron_no, book_title, status, reservation_date, reservation_date_exp)
        VALUES (ACCESSION_NO_SEQ.nextval, p_isbn, p_patron_no, p_book_title, 'RESERVED', sysdate, sysdate + 7);
    END IF;
END;
/
create or replace procedure autoupdatereservation
is
    cursor cur is 
    select isbn_no, reservation_date, reservation_date_exp
    from transaction
    where status = 'RESERVED';
begin
    for c in cur loop
        if sysdate > c.reservation_date_exp then 
            update transaction
            set status = 'RESERVE EXPIRED'
            where status = 'RESERVED' AND reservation_date_exp = c.reservation_date_exp;
            
            update book
            set current_status = 'STORED'
            where isbn_no = c.isbn_no;
        End if;
    end loop;
end;
/
CREATE OR REPLACE PROCEDURE retrieveusers
(p_loginid IN users.loginid%TYPE, c_cursor OUT SYS_REFCURSOR)
AS 
BEGIN
    OPEN c_cursor FOR
        SELECT loginid, last_name, first_name, middle_name 
        FROM users
        WHERE loginid like p_loginid || '%';
END;
/
CREATE OR REPLACE PROCEDURE getPatronTransactionCount
(p_patron_no IN patron.patron_no%TYPE,
 p_count OUT NUMBER)
AS
BEGIN
    SELECT COUNT(*) INTO p_count
    FROM transaction
    WHERE patron_no = p_patron_no AND status IN ('RESERVED', 'WITHDRAWN');
END;