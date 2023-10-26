import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int userID;
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

    public List<Book> getBorrowBooks() {
        return borrowBooks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setBorrowBooks(List<Book> borrowBooks) {
        this.borrowBooks = borrowBooks;
    }

    public void checkInBook(Book book) {
        book.setQuantity(book.getQuantity() - 1);
        book.setStatus(true);

    }
    public void checkOutBook(Book book) {
        book.setQuantity(book.getQuantity() + 1);
        book.setStatus(false);
        borrowBooks.add(book);
    }
    //For this method need to make file for checkout history
   // public void getCheckoutHistory(){}


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userID=" + userID +
                ", borrowBooks=" + borrowBooks +
                '}';
    }
}
