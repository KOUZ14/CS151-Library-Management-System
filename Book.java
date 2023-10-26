public class Book {
    private String title;
    private String author;
    private int ISBN;
    private boolean status;
    private int quantity;

    public Book(){}
    public Book(String title, String author, int ISBN, boolean status, int quantity) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.status = status;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }

    public boolean isStatus() {
        return status;
    }

    public int getISBN() {
        return ISBN;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
