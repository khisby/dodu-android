package id.khisoft.dodu.entity;

public class Pengguna {
    private int id;
    private String nama;
    private String surel;
    private String sandi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSurel() {
        return surel;
    }

    public void setSurel(String surel) {
        this.surel = surel;
    }

    public String getSandi() {
        return sandi;
    }

    public void setSandi(String sandi) {
        this.sandi = sandi;
    }
}
