package id.khisoft.dodu.entity;

public class Laporan {
    private String kategori;
    private int jumlah;
    private int keluar;
    private int masuk;
    private int total;


    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getKeluar() {
        return keluar;
    }

    public void setKeluar(int keluar) {
        this.keluar = keluar;
    }

    public int getMasuk() {
        return masuk;
    }

    public void setMasuk(int masuk) {
        this.masuk = masuk;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
