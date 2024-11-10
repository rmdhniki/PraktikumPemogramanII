package Latihan2_P7.src.dao;

import Latihan2_P7.src.db.MySqlConnection;
import Latihan2_P7.src.model.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        Connection conn = MySqlConnection.getInstance().getConnection();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM buku");

            while (rs.next()) {
                Book book = new Book(
                    rs.getString("id"),
                    rs.getString("judul"),
                    rs.getString("pengarang"),
                    rs.getString("tahun_penerbit"),
                    rs.getString("penerbit")
                );
                books.add(book);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }
}
