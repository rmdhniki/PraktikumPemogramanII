package Latihan2_P7.src.view.book;

import Latihan2_P7.src.dao.BookDao;
import Latihan2_P7.src.model.Book;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class BookFrame extends JFrame {
    private BookDao bookDao;

    public BookFrame() {
        this.bookDao = new BookDao();
        List<Book> bookList = bookDao.findAll();

        // Membuat model tabel
        TableModel tableModel = new BookTableModel(bookList);

        // Membuat tabel
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Menyusun komponen
        this.setTitle("Data Buku");
        this.setLayout(new BorderLayout());
        this.add(new JLabel("Data Buku", SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        // Menyusun frame
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BookFrame().setVisible(true);
        });
    }
}
