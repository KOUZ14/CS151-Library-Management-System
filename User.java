import java.io.Serializable;
import java.sql.*;
import java.util.List;

public class User implements Serializable {
    private String name;
    private int userID;
    private char[] password;
    List<Book> borrowBooks;

    public User(){}
    public User(String name, int userID, List<Book> borrowBooks) {
        this.name = name;
        this.userID = userID;
        this.borrowBooks = borrowBooks;
    }

    public String getName() {
        return name;
    }

    public int getUserID() {
        return userID;
    }

    public char[] getPassword() {
        return password;
    }

    public List<Book> getBorrowBooks() {
        return borrowBooks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public void setBorrowBooks(List<Book> borrowBooks) {
        this.borrowBooks = borrowBooks;
    }

    public void checkInBook(int bid) {
        // book.setQuantity(book.getQuantity() - 1);
        // book.setStatus(true);
        Connection conn = null;
        try {
            conn = Library.conn();
            assert conn != null;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Issued WHERE UID = ? AND BID = ?");
            ps.setInt(1, userID);
            ps.setInt(2, bid);
            ps.executeUpdate();
            PreparedStatement p = conn.prepareStatement("UPDATE Books SET Status = ? WHERE BID = ?");
            p.setBoolean(1, true);
            p.setInt(2, bid);
            p.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void checkOutBook(int bid) {
        //book.setQuantity(book.getQuantity() + 1);
        //book.setStatus(false);
        // borrowBooks.add(book);
        Connection conn = null;
        try {
            conn = Library.conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Issued (UID, BID, Borrow_Date, Return_Date) VALUES (?, ?, ?, ?)");
            ps.setInt(1, getUserID());
            ps.setInt(2, bid);
            long millis = System.currentTimeMillis();
            Date d1 = new Date(millis);
            ps.setDate(3, d1);
            Date d2 = new Date(millis + 86000000);
            ps.setDate(4, d2);
            ps.executeUpdate();
            PreparedStatement p = conn.prepareStatement("UPDATE Books SET Status = ? WHERE BID = ?");
            p.setBoolean(1, false);
            p.setInt(2, bid);
            p.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public boolean status() {
        Connection conn = null;
        try {
            conn = Library.conn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE libsystest");
            PreparedStatement ps = conn.prepareStatement("SELECT count(UID) FROM ISSUED WHERE UID =?");
            ps.setInt(1, getUserID());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                if(rs.getInt(1) > 5) {
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }
    //For this method need to make file for checkout history
   // public void getCheckoutHistory(){}

    //String sql = "SELECT DISTINCT User_Books.*, Books.Title, Books.Author, Books.ISBN FROM User_Books, Books WHERE "


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userID=" + userID +
                ", borrowBooks=" + borrowBooks +
                '}';
    }
}
