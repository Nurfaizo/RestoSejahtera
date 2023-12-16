/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resto_sejahtera;

/**
 *
 * @author Faiz
 */
public class Order {
    private String Nama_Produk;
    private int Jumlah;
    private int Total_Harga;

    public Order(String Nama_Produk, int Jumlah, int Total_Harga) {
        this.Nama_Produk = Nama_Produk;
        this.Jumlah = Jumlah;
        this.Total_Harga = Total_Harga;
    }

    public String getNama_Produk() {
        return Nama_Produk;
    }

    public void setNama_Produk(String Nama_Produk) {
        this.Nama_Produk = Nama_Produk;
    }

    public int getJumlah() {
        return Jumlah;
    }

    public void setJumlah(int Jumlah) {
        this.Jumlah = Jumlah;
    }

    public int getTotal_Harga() {
        return Total_Harga;
    }

    public void setTotal_Harga(int Total_Harga) {
        this.Total_Harga = Total_Harga;
    }
}
