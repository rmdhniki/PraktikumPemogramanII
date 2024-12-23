package model;

import java.math.BigDecimal;

public class Book {

        private int id; // Primary Key
        private String namaGame;
        private String studio;
        private String penerbit;
        private int rilis;
        private String genre;
        private BigDecimal harga;

    // Constructor dengan parameter (untuk inisialisasi data)
    public Book(String namaGame, String studio, String penerbit, int rilis, String genre, BigDecimal harga) {
        this.namaGame = namaGame;
        this.studio = studio;
        this.penerbit = penerbit;
        this.rilis = rilis;
        this.genre = genre;
        this.harga = harga;
    }

    // Default Constructor (penting untuk MyBatis)
    public Book() {
    }

    // Getter dan Setter dengan validasi sederhana
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
    }

    public String getNamaGame() {
        return namaGame;
    }

    public void setNamaGame(String namaGame) {
        if (namaGame != null && !namaGame.trim().isEmpty()) {
            this.namaGame = namaGame;
        } else {
            throw new IllegalArgumentException("Nama Game cannot be null or empty");
        }
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        if (studio != null && !studio.trim().isEmpty()) {
            this.studio = studio;
        } else {
            throw new IllegalArgumentException("Studio cannot be null or empty");
        }
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        if (penerbit != null && !penerbit.trim().isEmpty()) {
            this.penerbit = penerbit;
        } else {
            throw new IllegalArgumentException("Penerbit cannot be null or empty");
        }
    }

    public int getRilis() {
        return rilis;
    }

    public void setRilis(int rilis) {
        if (rilis > 0) {
            this.rilis = rilis;
        } else {
            throw new IllegalArgumentException("Rilis must be greater than 0");
        }
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (genre != null && !genre.trim().isEmpty()) {
            this.genre = genre;
        } else {
            throw new IllegalArgumentException("Genre cannot be null or empty");
        }
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        if (harga != null && harga.compareTo(BigDecimal.ZERO) >= 0) {
            this.harga = harga;
        } else {
            throw new IllegalArgumentException("Harga must be non-negative");
        }
    }

    // Override toString() untuk debugging dan logging
    @Override
    public String toString() {
        return "Book{"
                + "id=" + id
                + ", namaGame='" + namaGame + '\''
                + ", studio='" + studio + '\''
                + ", penerbit='" + penerbit + '\''
                + ", rilis=" + rilis
                + ", genre='" + genre + '\''
                + ", harga=" + harga
                + '}';
    }
}