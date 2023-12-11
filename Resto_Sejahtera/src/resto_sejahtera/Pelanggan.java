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
    
    public Pelanggan(int ID, String nama, String alamat, String mpDisukai) {
        this.ID = ID;
        this.nama = nama;
        this.alamat = alamat;
        this.mpDisukai = mpDisukai;
    }

    public String getMpDisukai() {
        return mpDisukai;
    }

    public void setMpDisukai(String mpDisukai) {
        this.mpDisukai = mpDisukai;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public void buatPesanan() {
        
    }
    
    public void lihatMenu() {
        
    }
    
    public void tambahProduk() {
        
    }
}
