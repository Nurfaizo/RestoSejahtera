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
    private String metodePembayaran;
    private LocalDate tanggal;
    private int jumlahPembayaran;

    public Pembayaran(int ID, String metodePembayaran, LocalDate tanggal, int jumlahPembayaran) {
        this.ID = ID;
        this.metodePembayaran = metodePembayaran;
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
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
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
