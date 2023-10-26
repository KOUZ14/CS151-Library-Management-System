import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Library {
    private List<User> users;
    private List<Book> books;

    public Library(){}
    public Library(List<User> users, List<Book> books) {
        this.users = users;
        this.books = books;
    }

    public void addUser(User user) {
        users.add(user);
    }
    public void removeUser(User user) {
        users.remove(user);
    }
    //Need separate method to add more books to existing books
    public void addBook(Book book) {
        books.add(book);
    }
    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> getAvailableBooks() {
        return books;
    }
    public User getUserByID(int userID) {
        for(User user : users) {
            if(user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }
    public List<Book> searchBooksByAuthor(String author) {
        List<Book> tempBooks = new ArrayList<Book>() {
        };
        for(Book book : books) {
            if(Objects.equals(book.getAuthor(), author)) {
                tempBooks.add(book);
            }
        }
        return tempBooks;
    }
    public Book searchBooksByTitle(String title) {
        for (Book book : books) {
            if (Objects.equals(book.getTitle(), title)) {
                return book;
            }
        }
        return null;
    }
}
