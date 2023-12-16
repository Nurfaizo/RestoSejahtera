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
public class Pemesanan {
    private int Pemesanan_ID;
    private String Nama_Pelanggan;
    private LocalDate Tanggal;
    private int Total_Harga;
    private String Status;

    public Pemesanan(int Pemesanan_ID, String Nama_Pelanggan, LocalDate Tanggal, int Total_Harga, String Status) {
        this.Pemesanan_ID = Pemesanan_ID;
        this.Nama_Pelanggan = Nama_Pelanggan;
        this.Tanggal = Tanggal;
        this.Total_Harga = Total_Harga;
        this.Status = Status;
    }

    public int getPemesanan_ID() {
        return Pemesanan_ID;
    }

    public void setPemesanan_ID(int Pemesanan_ID) {
        this.Pemesanan_ID = Pemesanan_ID;
    }

    public String getNama_Pelanggan() {
        return Nama_Pelanggan;
    }

    public void setNama_Pelanggan(String Nama_Pelanggan) {
        this.Nama_Pelanggan = Nama_Pelanggan;
    }

    public LocalDate getTanggal() {
        return Tanggal;
    }

    public void setTanggal(LocalDate Tanggal) {
        this.Tanggal = Tanggal;
    }

    public int getTotal_Harga() {
        return Total_Harga;
    }

    public void setTotal_Harga(int Total_Harga) {
        this.Total_Harga = Total_Harga;
    }

    public String isStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
