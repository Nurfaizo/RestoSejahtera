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
    private int ID;
    private LocalDate tanggal;
    private boolean statusPemesanan;

    public Pemesanan(int ID, LocalDate tanggal, boolean statusPemesanan) {
        this.ID = ID;
        this.tanggal = tanggal;
        this.statusPemesanan = statusPemesanan;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public boolean isStatusPemesanan() {
        return statusPemesanan;
    }

    public void setStatusPemesanan(boolean statusPemesanan) {
        this.statusPemesanan = statusPemesanan;
    }
    
    public int hitungTotal() {
        return 0;
    }
}
