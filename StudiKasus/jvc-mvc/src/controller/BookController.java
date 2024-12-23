package controller;

import org.apache.ibatis.session.SqlSession;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Book;
import model.BookMapper;
import model.MyBatisUtil;
import view.BookView;
import javax.swing.JOptionPane;
import java.math.BigDecimal;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BookController {

    private BookView bookView;
    private BookMapper bookMapper;

    public BookController(BookView bookView) {
        this.bookView = bookView;

        // Tambahkan Listener ke View
        bookView.addAddBookListener(new AddBookListener());
        bookView.addUpdateBookListener(new UpdateBookListener());
        bookView.addDeleteBookListener(new DeleteBookListener());
        bookView.addRefreshButtonListener(new RefreshButtonListener());
        bookView.addTableSelectionListener(new TableSelectionListener());

        refreshBookTable(); // Awal load, refresh data tabel
    }

    // Method untuk refresh data tabel
    private void refreshBookTable() {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            bookMapper = session.getMapper(BookMapper.class);
            List<Book> books = bookMapper.getAllBooks();
            bookView.updateBookTable(books);
            bookView.clearInputFields(); // Kosongkan form input
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(bookView, "Error: " + ex.getMessage());
        }
    }

    // Listener untuk menambahkan game
    class AddBookListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Ambil input
                String namaGame = bookView.getNamaGameField().getText();
                String studio = bookView.getStudioField().getText();
                String penerbit = bookView.getPenerbitField().getText();
                int rilis = Integer.parseInt(bookView.getRilisField().getText());
                String genre = bookView.getGenreField().getText();
                BigDecimal harga = new BigDecimal(bookView.getHargaField().getText());

                // Validasi input kosong
                if (namaGame.isEmpty() || studio.isEmpty() || penerbit.isEmpty() || genre.isEmpty()) {
                    JOptionPane.showMessageDialog(bookView, "Please fill in all fields.");
                    return;
                }

                // Simpan game ke database
                Book book = new Book(namaGame, studio, penerbit, rilis, genre, harga);
                try (SqlSession session = MyBatisUtil.getSqlSession()) {
                    bookMapper = session.getMapper(BookMapper.class);
                    bookMapper.addBook(book);
                    session.commit();
                    refreshBookTable();
                    JOptionPane.showMessageDialog(bookView, "Game added successfully!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(bookView, "Error: " + ex.getMessage());
            }
        }
    }

    // Listener untuk memperbarui game
    class UpdateBookListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = bookView.getTable().getSelectedRow();
            if (selectedRow >= 0) {
                try {
                    int id = (int) bookView.getTable().getValueAt(selectedRow, 0);

                    String namaGame = bookView.getNamaGameField().getText();
                    String studio = bookView.getStudioField().getText();
                    String penerbit = bookView.getPenerbitField().getText();
                    int rilis = Integer.parseInt(bookView.getRilisField().getText());
                    String genre = bookView.getGenreField().getText();
                    BigDecimal harga = new BigDecimal(bookView.getHargaField().getText());

                    Book book = new Book(namaGame, studio, penerbit, rilis, genre, harga);
                    book.setId(id);

                    try (SqlSession session = MyBatisUtil.getSqlSession()) {
                        bookMapper = session.getMapper(BookMapper.class);
                        bookMapper.updateBook(book);
                        session.commit();
                        refreshBookTable();
                        JOptionPane.showMessageDialog(bookView, "Game updated successfully!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(bookView, "Error: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(bookView, "Please select a game to update.");
            }
        }
    }

    // Listener untuk menghapus game
    class DeleteBookListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = bookView.getTable().getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) bookView.getTable().getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(bookView, "Delete this game?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try (SqlSession session = MyBatisUtil.getSqlSession()) {
                        bookMapper = session.getMapper(BookMapper.class);
                        bookMapper.deleteBook(id);
                        session.commit();
                        refreshBookTable();
                        JOptionPane.showMessageDialog(bookView, "Game deleted successfully!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(bookView, "Error: " + ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(bookView, "Please select a game to delete.");
            }
        }
    }

    // Listener untuk tombol refresh
    class RefreshButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshBookTable();
        }
    }

    // Listener untuk memilih data di tabel
    public class TableSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = bookView.getTable().getSelectedRow();
                if (selectedRow >= 0) {
                    bookView.getNamaGameField().setText(bookView.getTable().getValueAt(selectedRow, 1).toString());
                    bookView.getStudioField().setText(bookView.getTable().getValueAt(selectedRow, 2).toString());
                    bookView.getPenerbitField().setText(bookView.getTable().getValueAt(selectedRow, 3).toString());
                    bookView.getRilisField().setText(bookView.getTable().getValueAt(selectedRow, 4).toString());
                    bookView.getGenreField().setText(bookView.getTable().getValueAt(selectedRow, 5).toString());
                    bookView.getHargaField().setText(bookView.getTable().getValueAt(selectedRow, 6).toString());
                }
            }
        }
    }
}
