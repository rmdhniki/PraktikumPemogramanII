package model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import java.util.List;

public interface BookMapper {

    @Select("SELECT * FROM games")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "namaGame", column = "nama_game"),
            @Result(property = "studio", column = "studio"),
            @Result(property = "penerbit", column = "penerbit"), // Kolom publisher
            @Result(property = "rilis", column = "rilis"),
            @Result(property = "genre", column = "genre"),
            @Result(property = "harga", column = "harga")
    })
    List<Book> getAllBooks();

    @Insert("INSERT INTO games (nama_game, studio, penerbit, rilis, genre, harga) "
            + "VALUES (#{namaGame}, #{studio}, #{penerbit}, #{rilis}, #{genre}, #{harga})")
    void addBook(Book book);

    @Update("UPDATE games SET nama_game = #{namaGame}, studio = #{studio}, penerbit = #{penerbit}, "
            + "rilis = #{rilis}, genre = #{genre}, harga = #{harga} WHERE id = #{id}")
    void updateBook(Book book);

    @Delete("DELETE FROM games WHERE id = #{id}")
    void deleteBook(int id);
}

