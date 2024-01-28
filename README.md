This is a basic Library Management System developed using Java Spring Boot. It provides functionalities for managing books, patron, and borrowing/returning of books.

Features:
---------
CRUD operations for books
CRUD operations for patrons
Borrowing/returning books
Logging
Caching

Technologies Used:
------------------
Java
Spring Boot
Spring JPA
Spring Cache
H2 Database
Lombok
Maven
JUnit
Mockito

Prerequisites:
--------------
Java
Maven

Installation:
-------------
1. Clone the repository:
   git clone https://github.com/sonivishal189/library-management-system
2. Build the project using Maven:
   cd library-management-system
   mvn clean install
3. Run the application:
   mvn spring-boot:run
4. Test/Call APIs (Postman)

Endpoints:
----------
**Book Management**
1. GET /api/books: Retrieve a list of all books.
2. GET /api/books/{id}: Retrieve details of a specific book by ID.
3. POST /api/books: Add a new book to the library.
4. PUT /api/books/{id}: Update an existing book's information.
5. DELETE /api/books/{id}: Remove a book from the library.

**Patron Management**
1. GET /api/patrons: Retrieve a list of all patrons.
2. GET /api/patrons/{id}: Retrieve details of a specific patron by ID.
3. POST /api/patrons: Add a new patron to the system.
4. PUT /api/patrons/{id}: Update an existing patron's information.
5. DELETE /api/patrons/{id}: Remove a patron from the system.

**Borrowing/Returning**
1. POST /api/borrow/{bookId}/patron/{patronId}: Allow a patron to borrow a book.
2. PUT /api/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron.

Exception handling is done using Exception Handler which retuns proper error message with appropiate HTTP status code.
Logging is done using Lombok Slf4j. All the logs are traced on console.
Using spring cache all the books and patron responses are cached and updated or deleted according to the operations performed.
Services layer operations are tested using JUnit and Mockito with 100% code coverage.

All these datas are stored in H2 database in BOOK, PATRON and BORROWING_RECORD tables. 
H2 database can be accessed using h2-console at http://localhost:8080/h2-console/ using username "sa" and password "password"

