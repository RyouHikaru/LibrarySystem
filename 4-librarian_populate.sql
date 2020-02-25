CREATE SEQUENCE L_id_seq3
	INCREMENT BY 10
	START WITH 21000
	MINVALUE 21000
	NOMAXVALUE
	NOCYCLE
	NOCACHE;
BEGIN
	addUser(L_id_seq3.NEXTVAL, 'password', 'Amos', 'Saima', 'Graves', 'Librarian');
	addUser(L_id_seq3.NEXTVAL, 'password', 'Walker', 'Sofia', 'Barrow', 'Librarian');
	addUser(L_id_seq3.NEXTVAL, 'password', 'Hoffman', 'Harlow','Morssion', 'Librarian');
	addUser(L_id_seq3.NEXTVAL, 'password', 'Sharples', 'Marius','Holder', 'Librarian');
	addUser(L_id_seq3.NEXTVAL, 'password', 'Sawyer', 'Riaan', 'Eduardo', 'Librarian');
	DBMS_OUTPUT.PUT_LINE('Rows Created: ' || SQL%ROWCOUNT);
END;
