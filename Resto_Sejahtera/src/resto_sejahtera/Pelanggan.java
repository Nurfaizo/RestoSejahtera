/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resto_sejahtera;

/**
 *
 * @author Farhan Dzaky Aldias
 */
public class Pelanggan extends Pengguna{
    private String mpDisukai;

    public Pelanggan(int id_pengguna, String nama_pengguna, String alamat, String user_name, String PASSWORD, String mpDisukai) {
        this.id_pengguna = id_pengguna;
        this.nama_pengguna = nama_pengguna;
        this.alamat = alamat;
        this.user_name = user_name;
        this.PASSWORD = PASSWORD;
        this.mpDisukai = mpDisukai;
    }

    public String getMpDisukai() {
        return mpDisukai;
    }

    public void setMpDisukai(String mpDisukai) {
        this.mpDisukai = mpDisukai;
    }

    public int getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(int id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public String getNama_pengguna() {
        return nama_pengguna;
    }

    public void setNama_pengguna(String nama_pengguna) {
        this.nama_pengguna = nama_pengguna;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
