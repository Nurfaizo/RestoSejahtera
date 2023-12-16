/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resto_sejahtera;

import java.time.LocalDate;

/**
 *
 * @author Farhan Dzaky Aldias
 */
public class Pembayaran {
    private int ID;
    private String MetodePembayaran;
    private LocalDate tanggal;
    private int jumlahPembayaran;

    public Pembayaran(int ID, String MetodePembayaran, LocalDate tanggal, int jumlahPembayaran) {
        this.ID = ID;
        this.MetodePembayaran = MetodePembayaran;
        this.tanggal = tanggal;
        this.jumlahPembayaran = jumlahPembayaran;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMetodePembayaran() {
        return MetodePembayaran;
    }

    public void setMetodePembayaran(String MetodePembayaran) {
        this.MetodePembayaran = MetodePembayaran;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public int getJumlahPembayaran() {
        return jumlahPembayaran;
    }

    public void setJumlahPembayaran(int jumlahPembayaran) {
        this.jumlahPembayaran = jumlahPembayaran;
    }
    
    public void konfirmasiPembayaran() {
        
    }
}
