import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {
    private List<User> users;
    private List<Book> books;

    public Library(){
        users = new ArrayList<User>();
    }
    public Library(List<User> users, List<Book> books) {
        users = new ArrayList<User>();
        this.books = books;
    } 

    public void addUser(User user)  {
        //users.add(user);
        try {
            Connection conn = conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Users (Name, Password) VALUES (?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, String.valueOf(user.getPassword()));
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void removeUser(int UID) {
        //users.remove(user);
        Connection conn = null;
        try {
            conn = conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Users WHERE UID = ?");
            ps.setInt(1, UID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //Need separate method to add more books to existing books
    public static void addBook(Book book) {
        // books.add(book);
        Connection conn = null;
        try {
            conn = conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Books (Title, Author, ISBN, Status) VALUES (?, ?, ?, ?)");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getISBN());
            ps.setBoolean(4, book.isStatus());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void removeBook(int BID) {
       // books.remove(book);
        Connection conn = null;
        try {
            conn = conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Books WHERE BID = ?");
            ps.setInt(1, BID);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getAvailableBooks() {
        Connection conn = null;
        try {
            conn = conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            String sql = "SELECT * from Books";
            return stmt.executeQuery(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet getUsers() {
        Connection conn = null;
        try {
            conn = conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            String sql = "SELECT * from Users";
            return stmt.executeQuery(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getMyBooks(int uid) {
        Connection conn = null;
        try {
            conn = conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            String sql = "SELECT Issued.BID, Issued.Borrow_Date, Issued.Return_Date, Books.Title, Books.Author, Books.ISBN FROM Books, Issued WHERE Books.BID = Issued.BID AND Issued.UID =" + uid;
           // DBTablePrinter.printTable(conn, "Issued");
            return stmt.executeQuery(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getUserByID(int userID) {
        /*
        for(User user : users) {
            if(user.getUserID() == userID) {
                return user;
            }
        }
        return null;
         */
        Connection conn = null;
        try {
            conn = conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            String sql = "SELECT * FROM Users WHERE Id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            return ps.executeQuery();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet searchBooksByAuthor(String author) {
        /*
        List<Book> tempBooks = new ArrayList<Book>() {
        };
        for(Book book : books) {
            if(Objects.equals(book.getAuthor(), author)) {
                tempBooks.add(book);
            }
        }
        return tempBooks;
         */
        Connection conn = null;
        try {
            conn = conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            String sql = "SELECT * FROM Books WHERE Author = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, author);
            return ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet searchBooksByTitle(String title) {
        /*
        for (Book book : books) {
            if (Objects.equals(book.getTitle(), title)) {
                return book;
            }
        }
        return null;

         */
        Connection conn = null;
        try {
            conn = conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            String sql = "SELECT * FROM Books WHERE Title = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            return ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet validateUser(int uid, String password) {
        Connection conn = null;
        try {
            conn = conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            String sql = "SELECT * FROM Users WHERE UID = ? AND Password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.setString(2, password);
            return ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getUID(String userName, String password ) {

        Connection conn = null;
        try {
            conn = conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            String sql = "SELECT UID FROM Users WHERE Name = ? AND Password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("UID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public boolean checkStatus(int bid) {
        Connection conn = null;
        try {
            conn = conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            String sql = "SELECT Status FROM Books WHERE BID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getBoolean("Status");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public static Connection conn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "password");
    }

    public static void create() {
        Connection conn = null;
        try {
            conn = conn();
            Statement stmt = conn.createStatement();
            String sql = "CREATE DATABASE libsystest";
            stmt.executeUpdate(sql);
            stmt.executeUpdate("USE libsystest");
            String sql1 = "CREATE TABLE Users(UID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(30), Password VARCHAR(30))";
            stmt.executeUpdate(sql1);
            String sql4 = "INSERT INTO Users (Name, Password) VALUES ('admin', 'admin')";
            stmt.executeUpdate(sql4);
            String sql2 = "CREATE TABLE Issued (UID INT, BID INT, Borrow_Date DATE, Return_Date DATE)";
            stmt.executeUpdate(sql2);
            String sql3 = "CREATE TABLE Books(BID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Title VARCHAR(50), Author VARCHAR(30), ISBN INT, Status BOOLEAN)";
            stmt.executeUpdate(sql3);
        } catch (SQLException sqlException) {
            if (sqlException.getErrorCode() == 1007) {
                System.out.println(sqlException.getMessage());
            } else {
                sqlException.printStackTrace();
            }
        }


    }
}
