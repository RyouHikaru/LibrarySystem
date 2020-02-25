CREATE SEQUENCE id_seq2
	INCREMENT BY 10
	START WITH 20000
	MINVALUE 20000
	NOMAXVALUE
	NOCYCLE
	NOCACHE;
BEGIN
    addUser(id_seq2.NEXTVAL, 'password', 'Medina', 'Naveed', 'Hewitt', 'Patron');
	addUser(id_seq2.NEXTVAL, 'password', 'Lynn', 'Delhi', 'Storey', 'Patron');
	addUser(id_seq2.NEXTVAL, 'password', 'kaushik', 'Kota', 'Mcarthuir', 'Patron');
	addUser(id_seq2.NEXTVAL, 'password', 'Chaitali', 'Mumbai','Gardiner', 'Patron');
	addUser(id_seq2.NEXTVAL, 'password', 'Hardik', 'Bhopal','Sweeney', 'Patron' );
	addUser(id_seq2.NEXTVAL, 'password', 'Komal', 'MP', 'Pena', 'Patron' );
	addUser(id_seq2.NEXTVAL, 'password', 'Lynch', 'Om', 'Fraser', 'Patron');
	addUser(id_seq2.NEXTVAL, 'password', 'Bowden', 'Hannah', 'Combs', 'Patron');
	addUser(id_seq2.NEXTVAL, 'password', 'Cabrera', 'Verity', 'Bowning', 'Patron');
	addUser(id_seq2.NEXTVAL, 'password', 'Parrish', 'Lorenzo','Lennon', 'Patron');
	addUser(id_seq2.NEXTVAL, 'password', 'Phelps', 'Kornelia','Esparza', 'Patron');
	addUser(id_seq2.NEXTVAL, 'password', 'Stafford', 'Noland', 'Lovell', 'Patron');
	addUser(id_seq2.NEXTVAL, 'password', 'Hines', 'Kendall', 'Portillio', 'Patron' );
	addUser(id_seq2.NEXTVAL, 'password', 'Bosconovitch', 'Alisha', 'Barron', 'Patron'  );
	addUser(id_seq2.NEXTVAL, 'password', 'Sharp', 'Tai', 'Grainger', 'Patron'  );
	addUser(id_seq2.NEXTVAL, 'password', 'Orozco', 'Jami','Parria', 'Patron'  );
	addUser(id_seq2.NEXTVAL, 'password', 'Mcneil', 'Becky','Samuels', 'Patron' );
	addUser(id_seq2.NEXTVAL, 'password', 'Oneil', 'Cydney', 'Brown', 'Patron' );
	addUser(id_seq2.NEXTVAL, 'password', 'Sheman', 'Lilie', 'Mays', 'Patron');
	addUser(id_seq2.NEXTVAL, 'password', 'Cool', 'Kid', 'Always', 'Patron');
	DBMS_OUTPUT.PUT_LINE('Rows Created: ' || SQL%ROWCOUNT);
END;