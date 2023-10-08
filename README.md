# CS151-Library-Management-System

# Team
Group: Star
Kousik, Huy, Isiah

# Problem
Looking to make a management system for the library to help it maintain and organize the collection of books found within the library in order to speed up the check-out process. This would not only lessen the burden on librarians but also allows users a much cleaner and easier experience in finding and checking out/in books.

# Assumptions, Op Env, Intended Usage
We will be using Java Swing for the GUI, Java OOP for our back-end, exception handling for edge cases, and File I/O(Database?) to store/retrieve data 

# High-Level Description
We plan to three classes (Book, Library, User) that will keep track of book data(a.k.a titles, isbn, etc.), User information(name, etc.), and books and users found within the library. With all of this data we also need a place to store it. We will be using files(or database) in order to store and retrieve all of this data. In addition to this, we will also utilize exception handling, in order to catch edge cases such as a user trying to request a book that is not available. In order for these features to then be palatable to the general user, we will use java swing to make our gui. This will include actions like retrieving/storing books, display list of books within the library, etc.(Login?).

# Functionality
Our solution would allow for many users to check-out books at once without there needing to be a line at the librarians desk with self check out/in. It would also allow for an easy way for users and librarians to check what books are in/out utilizing the data stored.

# Operations
Customer:
Check out/in books
login?
search for books?
sort?
display list of books
Admin/Librarian:
Display list of books
Display list of users
Add or remove users


