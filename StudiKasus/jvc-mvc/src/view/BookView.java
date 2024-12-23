package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import model.Book;

public class BookView extends JFrame {

    private JTable table;
    private JTextField namaGameField, studioField, penerbitField, rilisField, hargaField, genreField;
    private JButton addButton, updateButton, deleteButton, refreshButton;
    private JPanel panel;

    public BookView() {
        // Inisialisasi komponen
        setTitle("Game Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Membuat tabel
        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nama Game", "Studio", "Penerbit", "Rilis", "Genre", "Harga"}
        ));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Pilih hanya 1 baris

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel untuk form input
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2, 5, 5));

        formPanel.add(new JLabel("Nama Game:"));
        namaGameField = new JTextField();
        formPanel.add(namaGameField);

        formPanel.add(new JLabel("Studio:"));
        studioField = new JTextField();
        formPanel.add(studioField);

        formPanel.add(new JLabel("Penerbit:"));
        penerbitField = new JTextField();
        formPanel.add(penerbitField);

        formPanel.add(new JLabel("Rilis:"));
        rilisField = new JTextField();
        formPanel.add(rilisField);

        formPanel.add(new JLabel("Genre:"));
        genreField = new JTextField();
        formPanel.add(genreField);

        formPanel.add(new JLabel("Harga:"));
        hargaField = new JTextField();
        formPanel.add(hargaField);

        // Tombol aksi
        addButton = new JButton("Add Game");
        updateButton = new JButton("Update Game");
        deleteButton = new JButton("Delete Game");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    // Mendapatkan data dari form
    public JTextField getNamaGameField() {
        return namaGameField;
    }

    public JTextField getStudioField() {
        return studioField;
    }

    public JTextField getPenerbitField() {
        return penerbitField;
    }

    public JTextField getRilisField() {
        return rilisField;
    }

    public JTextField getHargaField() {
        return hargaField;
    }

    public JTextField getGenreField() {
        return genreField;
    }

    public JTable getTable() {
        return table;
    }

    // Menambahkan listener untuk tombol
    public void addAddBookListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addUpdateBookListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void addDeleteBookListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addRefreshButtonListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    public void addTableSelectionListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }

    // Memperbarui tabel game
    public void updateBookTable(List<Book> books) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Menghapus data lama
        for (Book book : books) {
            model.addRow(new Object[]{
                    book.getId(), book.getNamaGame(), book.getStudio(),
                    book.getPenerbit(), book.getRilis(), book.getGenre(), book.getHarga()
            });
        }
    }

    // Mengosongkan input fields
    public void clearInputFields() {
        namaGameField.setText("");
        studioField.setText("");
        penerbitField.setText("");
        rilisField.setText("");
        genreField.setText("");
        hargaField.setText("");
    }
}
