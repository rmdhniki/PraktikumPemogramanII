package Latihan2_P7.src.view.book;

import Latihan2_P7.src.model.Book;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BookTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Judul", "Pengarang", "Tahun Terbit", "Penerbit"};
    private final List<Book> data;

    public BookTableModel(List<Book> data) {
        this.data = data;
    }

    public int getColumnCount() { return columnNames.length; }
    public int getRowCount() { return data.size(); }
    public String getColumnName(int col) { return columnNames[col]; }

    public Object getValueAt(int row, int col) {
        Book book = data.get(row);
        switch (col) {
            case 0: return book.getId();
            case 1: return book.getJudul();
            case 2: return book.getPengarang();
            case 3: return book.getTahunPenerbit();
            case 4: return book.getPenerbit();
            default: return null;
        }
    }

    public boolean isCellEditable(int row, int col) { return false; }
}
