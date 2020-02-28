# LibrarySystem
Final Project in Database Systems

Functionalities and Limitation:

PATRON:
  1. Login - patron cannot login to Librarian Login Screen
  2. Search Book - search only by Title; clicking the search button with empty input will display all books.
                 - string matching is limited to the first letter or first word of the whole book title.
  3. Reserve Book - can only reserve one book at a time. No walk-in reservations.
  4. View Transactions - can only view his/her transaction history.
  5. Logout - select logout from the options in the Menu Bar.
  
LIBRARIAN:
  1. Login - librarian can login both in the Patron and Librarian Main Screen.
  2. Manage Books:
      a. Search Book - search only by Title; clicking the search button with empty input will display all books.
      b. Add Book - all fields except Place of Publication are REQUIRED. Duplicate books will not count.
      c. Edit Book - select one book; all fields are OPTIONAL.
      d. Delete Book - select one book; deleting the selected book will delete all related data inside the database.
  3. Manage Users: 
      a. Search User - search only by Login ID; clicking the search button with empty input will display all users.
      b. Add User - generated login id will be shown in the top; all fields are REQUIRED.
      c. Edit User - select one user; all fields are OPTIONAL.
      d. Delete User - select one user; deleting the selected user will delete all related data inside the database.
  4. Manage Transactions:
      a. Search Transaction - search only by Accession no.; clicking the search button with empty input will display all transactions.
      b. Add Transaction - WITHDRAWAL only. No walk-in reservations for patron in this context. All fields are REQUIRED.
      c. Edit Transaction - select one transaction record. If the selected record is RESERVED or STORED, WITHDRAWAL form will be                                  displayed. Else, RETURN form will be showed.
                         - all fields except Reserve date in WITHDRAWAL form are REQUIRED.
                         - all fields are REQUIRED in RETURN form..
  5. Logout - select logout from the options in the Menu Bar.
      
       
Note: 
- Dates for all kinds of transactions are SYSDATE
- Selecting many record will only choose the first record to be inked as the SELECTED record

