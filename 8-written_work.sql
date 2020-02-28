declare
    cursor c_book is
    select isbn_no, copy_no from book;
    
    cursor c_author is 
    select author_id from author;
    
    v_random NUMBER;
    v_bookno book.isbn_no%TYPE;
	v_copy_no book.copy_no%TYPE;
    v_author author.author_id%TYPE;
begin
    OPEN c_book;
    OPEN c_author;
    LOOP
        SELECT FLOOR(DBMS_RANDOM.Value(1990, 2016)) INTO v_random from dual;
        FETCH c_book INTO v_bookno, v_copy_no;
        FETCH c_author INTO v_author;
        
        exit when c_book%NOTFOUND AND c_author%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(v_bookno || ' ' || v_author);
        ADDWRITTENWORK(v_bookno, v_copy_no, v_author, v_random, '');
    END LOOP;
    CLOSE c_book;
    CLOSE c_author;
    DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT);
END;