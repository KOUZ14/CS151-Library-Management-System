import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// We defined a class named LibraryManagementGUI that extends JFrame, which is the main window for our GUI.
public class LibraryManagementGUI extends JFrame {  
    // We declared instance variables to store lists of books and users, as well as JList components to display these lists in the GUI.
    private ArrayList<String> books = new ArrayList<>();
    private ArrayList<String> users = new ArrayList<>();
    private JList<String> bookList;
    private JList<String> userList;

    public LibraryManagementGUI() {
        // Here, we set the title, size, and default close operation for the main JFrame.
        setTitle("Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // We created a mainPanel that uses a GridLayout with 2 rows and 3 columns to organize the various components of the GUI
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 3));

        // We created a JList for displaying books and users and configure it for single selection mode. We also wrapped it in a JScrollPanel for scrolling if the list gets long.
        bookList = new JList<>();
        bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bookList.setModel(new DefaultListModel<>());
        mainPanel.add(new JScrollPane(bookList));

        userList = new JList<>();
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setModel(new DefaultListModel<>());
        mainPanel.add(new JScrollPane(userList));

        // These are all the buttons for the various functions
        JButton addBookButton = new JButton("Add Book");
        mainPanel.add(addBookButton);

        JButton removeBookButton = new JButton("Remove Book");
        mainPanel.add(removeBookButton);

        JButton addUserButton = new JButton("Add User");
        mainPanel.add(addUserButton);

        JButton removeUserButton = new JButton("Remove User");
        mainPanel.add(removeUserButton);

        JButton checkoutButton = new JButton("Check Out Book");
        mainPanel.add(checkoutButton);

        JButton checkinButton = new JButton("Check In Book");
        mainPanel.add(checkinButton);

        add(mainPanel);

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookName = JOptionPane.showInputDialog("Enter book name: ");
                if (bookName != null && !bookName.isEmpty()) {
                    books.add(bookName);
                    updateBookList();
                }
            }
        });

        removeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = bookList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    books.remove(selectedIndex);
                    updateBookList();
                }
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = JOptionPane.showInputDialog("Enter username: ");
                if (userName != null && !userName.isEmpty()) {
                    users.add(userName);
                    updateUserList();
                } 
            }
        });

        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int selectedIndex = userList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    users.remove(selectedIndex);
                    updateUserList();
                }
            }
        });

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int bookIndex = bookList.getSelectedIndex();
                int userIndex = userList.getSelectedIndex();
                if (bookIndex >= 0 && userIndex >=0) {

                }
                else {
                    JOptionPane.showMessageDialog(null, "Please choose a book and user.");
                }
            }
        });

        checkinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int bookIndex = bookList.getSelectedIndex();
                if (bookIndex >= 0){

                }
                else {
                    JOptionPane.showMessageDialog(null, "Please choose a book.");
                }
            }
        });

        setVisible(true);
    }

    private void updateBookList() {
        DefaultListModel<String> model = (DefaultListModel<String>) bookList.getModel();
        model.clear();
        for (String book : books){
            model.addElement(book);
        }
    }

    private void updateUserList() {
        DefaultListModel<String> model = (DefaultListModel<String>) userList.getModel();
        model.clear();
        for (String user : users){
            model.addElement(user);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementGUI());
    }
}
