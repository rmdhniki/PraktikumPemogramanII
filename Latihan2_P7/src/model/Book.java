package Latihan2_P7.src.model;

public class Book {
    private String id;
    private String judul;
    private String pengarang;
    private String tahunPenerbit;
    private String penerbit;

    // Constructor, getters, and setters
    public Book(String id, String judul, String pengarang, String tahunPenerbit, String penerbit) {
        this.id = id;
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunPenerbit = tahunPenerbit;
        this.penerbit = penerbit;
    }

    // Getters
    public String getId() { return id; }
    public String getJudul() { return judul; }
    public String getPengarang() { return pengarang; }
    public String getTahunPenerbit() { return tahunPenerbit; }
    public String getPenerbit() { return penerbit; }
}
