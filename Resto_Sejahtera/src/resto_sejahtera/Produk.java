/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resto_sejahtera;

/**
 *
 * @author Farhan Dzaky Aldias
 */
public class Produk {
    private int id_product;
    private String nama_product;
    private String description;
    private int harga;

    public Produk(int id_product, String nama_product, String description, int harga) {
        this.id_product = id_product;
        this.nama_product = nama_product;
        this.description = description;
        this.harga = harga;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getNama_product() {
        return nama_product;
    }

    public void setNama_product(String nama_product) {
        this.nama_product = nama_product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
