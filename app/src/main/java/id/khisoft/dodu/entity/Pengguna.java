package id.khisoft.dodu.entity;

public class Pengguna {
    private String nama;
    private String surel;
    private String sandi;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
