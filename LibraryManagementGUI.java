import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

// We defined a class named LibraryManagementGUI that extends JFrame, which is the main window for our GUI.
public class LibraryManagementGUI extends JFrame {
    // We declared instance variables to store lists of books and users, as well as JList components to display these lists in the GUI.
    private ArrayList<String> books = new ArrayList<>();
    private ArrayList<String> users = new ArrayList<>();
    private JList<Book> bookList;
    private JList<User> userList;
    private JTable bookTable;
    private JTable userTable;
    private JTable myBookTable;
    private User user = new User();
    private Library library = new Library();

    public LibraryManagementGUI() {
        // Here, we set the title, size, and default close operation for the main JFrame.
        setTitle("Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Library.create();
        JPanel login = new JPanel();
        login.setLayout(null);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 3));

        final JPasswordField value = new JPasswordField();
        value.setBounds(100, 75, 100, 30);
        JLabel l1 = new JLabel("User ID:");
        l1.setBounds(20, 20, 80, 30);
        JLabel l2 = new JLabel("Password:");
        l2.setBounds(20, 75, 80, 30);
        JButton b = new JButton("Login");
        b.setBounds(100, 120, 80, 30);
        final JTextField text = new JTextField();
        text.setBounds(100, 20, 100, 30);
        login.add(value);
        login.add(l1);
        login.add(l2);
        login.add(b);
        login.add(text);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                user.setUserID(Integer.parseInt(text.getText()));
                user.setPassword(value.getPassword());
                try {
                    ResultSet rs = library.validateUser(user.getUserID(), String.valueOf(user.getPassword()));
                    if(rs.next()) {
                        updateMyBookList();
                        remove(login);
                        add(mainPanel);
                        repaint();
                        revalidate();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "User doesn't exist");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                /*
                remove(login);
                add(mainPanel);
                repaint();
                revalidate();

                 */
            }
        });

        add(login);

        // We created a mainPanel that uses a GridLayout with 2 rows and 3 columns to organize the various components of the GUI
       // JPanel mainPanel = new JPanel();
       // mainPanel.setLayout(new GridLayout(2, 3));

        // We created a JList for displaying books and users and configure it for single selection mode. We also wrapped it in a JScrollPanel for scrolling if the list gets long.
        bookList = new JList<>();
        bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bookList.setModel(new DefaultListModel<>());
        //mainPanel.add(new JScrollPane(bookList));

        userList = new JList<>();
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setModel(new DefaultListModel<>());
        //mainPanel.add(new JScrollPane(userList));

        bookTable = new JTable();
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        String header[] = new String[] { "BID", "Title", "Author", "ISBN",
                "Status" };
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        dtm.setColumnIdentifiers(header);
        bookTable.setModel(dtm);

        userTable = new JTable();
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        String header1[] = new String[] { "UID", "Name", "Password"};
        DefaultTableModel dtm1 = new DefaultTableModel(0, 0);
        dtm1.setColumnIdentifiers(header1);
        userTable.setModel(dtm1);

        myBookTable = new JTable();
        myBookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        String header2[] = new String[] { "BID", "Borrow Date", "Return Date", "Title", "Author", "ISBN"};
        DefaultTableModel dtm2 = new DefaultTableModel(0, 0);
        dtm2.setColumnIdentifiers(header2);
        myBookTable.setModel(dtm2);

        updateUserList();
        updateBookList();

        // These are all the buttons for the various functions
        JButton viewBookButton = new JButton("View Books");
        mainPanel.add(viewBookButton);

        JButton viewUserButton = new JButton("View Users");
        mainPanel.add(viewUserButton);

        JButton addBookButton = new JButton("Add Book");
        mainPanel.add(addBookButton);

        JButton addUserButton = new JButton("Add User");
        mainPanel.add(addUserButton);

        JButton viewMyBooks = new JButton("My Books");
        mainPanel.add(viewMyBooks);


        viewBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel bookPanel = new JPanel();
                bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
                //DefaultTableModel dtm = new DefaultTableModel(0, 0);
                //bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
               // String header[] = new String[] { "BID", "Title", "Author", "ISBN",
               //         "Status" };
                //dtm.setColumnIdentifiers(header);
               //bookTable.setModel(dtm);
                //dtm.addRow(new Object[] {1, "Hi", "Hello", 1234, false});
                bookPanel.add(new JScrollPane(bookTable));
                JPanel bookButtons = new JPanel();
                bookButtons.setLayout(new BoxLayout(bookButtons, BoxLayout.X_AXIS));
                bookPanel.add(bookButtons);
                JButton checkout = new JButton("Check-Out");
                checkout.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int bookIndex = bookTable.getSelectedRow();
                        if (bookIndex >= 0) {
                            int column = 0;
                            int row = bookTable.getSelectedRow();
                            String val = bookTable.getModel().getValueAt(row, column).toString();
                            if (library.checkStatus(Integer.parseInt(val))) {
                                user.checkOutBook(Integer.parseInt(val));
                                updateBookList();
                                updateMyBookList();
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Please choose a book.");
                        }
                    }
                });
                bookButtons.add(checkout);
                JButton removeBook = new JButton("Remove Book");
                removeBook.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int bookIndex = bookTable.getSelectedRow();
                        if (bookIndex >= 0) {
                            int column = 0;
                            int row = bookTable.getSelectedRow();
                            String val = bookTable.getModel().getValueAt(row, column).toString();
                            Library.removeBook(Integer.parseInt(val));
                            updateBookList();
                            updateMyBookList();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Please choose a book.");
                        }
                    }
                });
                bookButtons.add(removeBook);
                JButton backButton = new JButton("Back");
                backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        remove(bookPanel);
                        add(mainPanel);
                        repaint();
                        revalidate();
                    }
                });
                bookButtons.add(backButton);
                remove(mainPanel);
                add(bookPanel);
                repaint();
                revalidate();
            }
        });

       // add(mainPanel);

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog d = new JDialog(getOwner(), "Login");
                final JLabel label = new JLabel();
                label.setBounds(20, 195, 500, 50);
                JLabel l1 = new JLabel("Title:");
                l1.setBounds(20, 20, 80, 30);
                JLabel l2 = new JLabel("Author:");
                l2.setBounds(20, 75, 80, 30);
                JLabel l3 = new JLabel("ISBN:");
                l3.setBounds(20, 130, 80, 30);
                JButton b = new JButton("Add Book");
                b.setBounds(100, 240, 80, 30);
                final JTextField text = new JTextField();
                text.setBounds(100, 20, 100, 30);
                final JTextField text1 = new JTextField();
                text1.setBounds(100, 75, 100, 30);
                final JTextField text2 = new JTextField();
                text2.setBounds(100, 130, 100, 30);
                d.add(l1);
                d.add(label);
                d.add(l2);
                d.add(text1);
                d.add(l3);
                d.add(text2);
                d.add(b);
                d.add(text);
                d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                d.setSize(300, 300);
                d.setLocationRelativeTo(null);
                d.setLayout(null);
                //Blocks button usage on main JFrame page
                d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Book bookValue = new Book();
                        bookValue.setTitle(text.getText());
                        bookValue.setAuthor(text1.getText());
                        bookValue.setISBN(Integer.parseInt(text2.getText()));
                        bookValue.setStatus(true);
                        Library.addBook(bookValue);
                        updateBookList();
                        d.dispose();
                    }
                });
                d.setVisible(true);
            }
        });

        viewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel userPanel = new JPanel();
                userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
                JPanel userButtons = new JPanel();
                userButtons.setLayout(new BoxLayout(userButtons, BoxLayout.X_AXIS));
                //DefaultTableModel dtm = new DefaultTableModel(0, 0);
                //bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                // String header[] = new String[] { "BID", "Title", "Author", "ISBN",
                //         "Status" };
                //dtm.setColumnIdentifiers(header);
                //bookTable.setModel(dtm);
                //dtm.addRow(new Object[] {1, "Hi", "Hello", 1234, false});
                userPanel.add(new JScrollPane(userTable));
                userPanel.add(userButtons);
                JButton removeUser = new JButton("Remove User");
                removeUser.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int userIndex = userTable.getSelectedRow();
                        if (userIndex >= 0) {
                            int column = 0;
                            int row = userTable.getSelectedRow();
                            String val = userTable.getModel().getValueAt(row, column).toString();
                            Library.removeUser(Integer.parseInt(val));
                            updateUserList();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Please choose a book.");
                        }
                    }
                });
                userButtons.add(removeUser);
                JButton backButton = new JButton("Back");
                backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        remove(userPanel);
                        add(mainPanel);
                        repaint();
                        revalidate();
                    }
                });
                userButtons.add(backButton);
                remove(mainPanel);
                add(userPanel);
                repaint();
                revalidate();
            }
        });


        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog d = new JDialog(getOwner(), "Add User");
                final JPasswordField value = new JPasswordField();
                value.setBounds(100, 75, 100, 30);
                JLabel l1 = new JLabel("User Name:");
                l1.setBounds(20, 20, 80, 30);
                JLabel l2 = new JLabel("Password:");
                l2.setBounds(20, 75, 80, 30);
                JButton b = new JButton("Add User");
                b.setBounds(100, 130, 80, 30);
                final JTextField text = new JTextField();
                text.setBounds(100, 20, 100, 30);
                d.add(l1);
                d.add(l2);
                d.add(value);
                d.add(b);
                d.add(text);
                d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                d.setSize(300, 300);
                d.setLocationRelativeTo(null);
                d.setLayout(null);
                //Blocks button usage on main JFrame page
                d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        User userVal = new User();
                        userVal.setName(text.getText());
                        userVal.setPassword(value.getPassword());
                        library.addUser(userVal);
                        userVal.setUserID(library.getUID(userVal.getName(), String.valueOf(userVal.getPassword())));
                        JOptionPane.showMessageDialog(null, userVal.getUserID());
                        updateUserList();
                        d.dispose();
                    }
                });
                d.setVisible(true);
            }
        });

        viewMyBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel myBookPanel = new JPanel();
                myBookPanel.setLayout(new BoxLayout(myBookPanel, BoxLayout.Y_AXIS));
                JPanel myBookButtons = new JPanel();
                myBookButtons.setLayout(new BoxLayout(myBookButtons, BoxLayout.X_AXIS));
                //DefaultTableModel dtm = new DefaultTableModel(0, 0);
                //bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                // String header[] = new String[] { "BID", "Title", "Author", "ISBN",
                //         "Status" };
                //dtm.setColumnIdentifiers(header);
                //bookTable.setModel(dtm);
                //dtm.addRow(new Object[] {1, "Hi", "Hello", 1234, false});
                myBookPanel.add(new JScrollPane(myBookTable));
                myBookPanel.add(myBookButtons);
                JButton checkIn = new JButton("Check-In");
                checkIn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int myBookIndex = myBookTable.getSelectedRow();
                        if (myBookIndex >= 0) {
                            int column = 0;
                            int row = myBookTable.getSelectedRow();
                            String val = myBookTable.getModel().getValueAt(row, column).toString();
                            user.checkInBook(Integer.parseInt(val));
                            updateBookList();
                            updateMyBookList();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Please choose a book.");
                        }
                    }
                });
                myBookButtons.add(checkIn);

                JButton backButton = new JButton("Back");
                backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        remove(myBookPanel);
                        add(mainPanel);
                        repaint();
                        revalidate();
                    }
                });
                myBookButtons.add(backButton);
                remove(mainPanel);
                add(myBookPanel);
                repaint();
                revalidate();
            }
        });


        setVisible(true);
    }

    private void updateBookList() {
        DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
        dtm.setRowCount(0);
        ResultSet rs = Library.getAvailableBooks();
        try {
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5)});
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        //DefaultListModel<Book> model = (DefaultListModel<Book>) bookList.getModel();
        //model.clear();
       // model.addElement(book);
    }

    private void updateUserList() {
        DefaultTableModel dtm1 = (DefaultTableModel) userTable.getModel();
        dtm1.setRowCount(0);
        ResultSet rs = Library.getUsers();
        try {
            while (rs.next()) {
                dtm1.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3)});
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateMyBookList() {
        DefaultTableModel dtm2 = (DefaultTableModel) myBookTable.getModel();
        dtm2.setRowCount(0);
        ResultSet rs = Library.getMyBooks(user.getUserID());
        try {
            while (rs.next()) {
            dtm2.addRow(new Object[]{rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getInt(6)});
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementGUI());
    }
}