# CS151-Library-Management-System

# Team Star
Members: Kousik, Huy, Isiah

# Proposal Contributions
Kousik: Designed the class diagram and use case diagrams. Formatted the README file using MarkDown and added edits to it.  
Huy: Helped Isiah and Kousik with designing the all the diagrams and revised proposal.  
Isiah: Designed Sequence and State Diagram. Wrote down majority of our ideas on to readme file.

# Presentation Contributions
Kousik: Started out by giving run through demo of project and explained gui features and answered questions about format and why main panel was able to readjust to size and not login panel.  
Huy: Described a list of book library components as well as how library searching works. Explained how it works in creating usernames, passwords, and how it works in the library. Answered questions about class designs.  
Isiah: Gave some more details about the program, explained the how the database works and how it interacts with other parts of the program. Answered questions about the database.

# Project(Code) and Report Contributions
Kousik: Designed majority of the GUI such as main panel, dialogs, tables. Set up code for easy integration with the database. Used a GridLayout for the main panel to have a nice balanced layout. Incorporated Jlist components within a Jscroll in case the list gets long. Created all the necessary Jbuttons and implemented actionlistener for each action. Made a GitHub repository and formatted the report into README file.  
Huy: Made the custom exceptions as well as the backend classes. Create a username and password that allows users to access the application. Users can use their accounts to perform functions in the application such as: looking up the list of books, borrowing books, and checking out. The username can be set to any desired user, unless it matches an existing username; However, the password must be set according to specific requirements for security as well as duplication with other accounts. Handle exception-related issues such as: book existence, search with incorrect title, author name, or ISBN. Requirements related to password security must ensure not to be the same, special characters, numbers, and so on. Searches in the library will be checked using a "true/false" form, which allows checking the accuracy of the search.  
Isiah: Made the mysql database and tables within it by using a jdbc connector. I was able to code these elements within the project itself. Made the methods to interact with said database(retrieving and storing data) and connected everything to work with the database(things like what to do with the resultset from the database).

# Problem
Looking to make a management system for the library to help it maintain and organize the collection of books found within the library in order to speed up the check-out process. This would not only lessen the burden on librarians but also allow users a much cleaner and easier experience in finding and checking out/in books.

# Assumptions, Op Env, Intended Usage
We will be using Java Swing for the GUI, Java OOP for our back-end, exception handling for edge cases, and MySQL to store/retrieve data 

# Diagrams
Class Diagram: https://github.com/KOUZ14/CS151-Library-Management-System/blob/main/diagrams/Class%20Diagram.png  
Sequence Diagram: https://github.com/KOUZ14/CS151-Library-Management-System/blob/main/diagrams/Sequence%20Diagram.png  
State Diagram: https://github.com/KOUZ14/CS151-Library-Management-System/blob/main/diagrams/State%20Diagram.png  
Use Case Diagram: https://github.com/KOUZ14/CS151-Library-Management-System/blob/main/diagrams/Use%20Case%20Diagram.png  

# High-Level Description
We plan to have three classes (Book, Library, User) that will keep track of book data(a.k.a titles, ISBN, Brand (courses), etc.), User information(name, etc.), and books and users found within the library. With all of this data, we also need a place to store it. We will be using files(or databases) in order to store and retrieve all of this data. In addition to this, we will also utilize exception handling, in order to catch edge cases such as a user trying to request a book that is not available. In order for these features to then be palatable to the general user, we will use Java Swing to make our GUI. This will include actions like retrieving/storing books, displaying a list of books within the library, etc. (Login?).

# Functionality
Our solution would allow many users to check out books at once without there needing to be a line at the librarian's desk with self-checkout/in. It would also allow for an easy way for users and librarians to check what books are in/out utilizing the data stored.

# Operations

Customer:
Check out/in-books
Login/ Logout
display list of books

Admin/Librarian:
Display a list of books
Manage/ Display list of users
Add or remove users
Sign-up page (username/ password/email/ phone)
View Record
Add/update/remove quantity books
check out/in books

#Steps to Run Code
1. mysql-connector-j-8.2.0.jar should already be in the executable jar but may need to add that to file path
2. The mysql server running on Isiah's computer or else the code won't work(We were having trouble with connecting to the mysql server on other computers)
3. Once program is running, Userid: 1, Password: admin is the admin account and Userid: 2, Password: Password1! is an example user account

# Screenshots

Login Screen
<img width="599" alt="Screenshot 2023-11-28 at 7 14 10 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/9485af90-e5c8-4498-a989-f0b0f3403186">

Admin Account Main Screen
<img width="597" alt="Screenshot 2023-11-28 at 7 14 57 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/02c3d430-97b1-45ce-80a7-93124e1ad2ed">

User Account Main Screen
<img width="594" alt="Screenshot 2023-11-28 at 7 15 33 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/53b038b2-05c1-4505-ae98-1b97f518b536">

View Books Admin Screen
<img width="595" alt="Screenshot 2023-11-28 at 7 16 11 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/2c4535e9-9865-4092-9c37-b7935ad2b5ab">

View Books User Screen
<img width="586" alt="Screenshot 2023-11-28 at 7 16 43 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/a44e11f0-8356-4c79-b2f9-b944e42a646b">

No Book Selected
<img width="600" alt="Screenshot 2023-11-28 at 7 26 10 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/02ec30e3-deaf-44c8-bb87-1fb2c6ca94cc">

Book Unavailable
<img width="592" alt="Screenshot 2023-11-28 at 7 17 10 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/49368336-5a02-4a4d-8871-74fd67f42f70">

View Users Admin Only
<img width="600" alt="Screenshot 2023-11-28 at 7 18 25 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/70f0ec58-bae2-4886-ae9c-1de00e435347">

Add Book Admin Only
<img width="596" alt="Screenshot 2023-11-28 at 7 19 37 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/2230ff05-9aaf-4c84-9c6a-ca5a7970e565">

Title Already in Use Exception
<img width="598" alt="Screenshot 2023-11-28 at 7 22 06 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/778a7c0a-ccd0-4f92-bec7-2e729bb08ab0">

Only #'s in ISBN Exception
<img width="595" alt="Screenshot 2023-11-28 at 7 22 46 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/6ed11a51-2ae6-45e5-bf35-0b8b39f3e0cd">

Add User Admin Only
<img width="595" alt="Screenshot 2023-11-28 at 7 23 22 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/86c79332-cfb2-4ac7-9e2d-9a5c11234697">

Some Password Exceptions
<img width="594" alt="Screenshot 2023-11-28 at 7 23 57 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/4c86aed8-f63d-4ddc-a408-42fda4f9043a">
<img width="593" alt="Screenshot 2023-11-28 at 7 24 29 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/ae098242-a7a4-4175-ad8c-c6b87c10db1d">

View Issued Books Admin Only
<img width="594" alt="Screenshot 2023-11-28 at 7 24 58 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/597e3b47-a409-4d79-973a-a20ada5225f9">

View My Books User Only
<img width="595" alt="Screenshot 2023-11-28 at 7 25 52 PM" src="https://github.com/KOUZ14/CS151-Library-Management-System/assets/92612901/3a504221-b4ea-49e2-b5c9-26514c89d7fd">


