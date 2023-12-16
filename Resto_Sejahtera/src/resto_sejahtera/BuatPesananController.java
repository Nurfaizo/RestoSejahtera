package resto_sejahtera;

import java.sql.Date;
import java.time.LocalDate;
import db.DBHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static resto_sejahtera.LoginController.usename;



public class BuatPesananController implements Initializable{

    @FXML
    private TableColumn<Order, Integer> colHargap;

    @FXML
    private Button btBayar;
    
    @FXML
    private Button btBuat;

    @FXML
    private Button btHome;

    @FXML
    private Button btMenu;

    @FXML
    private Button btTambah;

    @FXML
    private TableColumn<Produk, String> colDeskripsi;

     @FXML
    private TableColumn<Produk, Integer> colHarga;

    @FXML
    private TableColumn<Order, Integer> colKuantitas;

    @FXML
    private TableColumn<Produk, String> colNama;
    
    @FXML
    private TableColumn<Produk, Integer> colID;

    @FXML
    private TableColumn<Order, String> colNamap;
    
    @FXML
    private ChoiceBox<Integer> cbID;

    @FXML
    private TextField tfKuantitas;

    @FXML
    private TableView<Produk> tvMenu;

    @FXML
    private TableView<Order> tvMenu1;
    
    @FXML
    private Label warn;
    
    ArrayList<Integer> id = new ArrayList<>();
    
    @FXML
    void Pembayaran(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Pembayaran");
        alert.setHeaderText("Apakah Anda yakin ingin melakukan pembayaran?");
         alert.setContentText("Total Pembayaran: " + getTotal() + " IDR");
        alert.initModality(Modality.APPLICATION_MODAL);

        ButtonType buttonTypeOK = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        
        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeOK) {
                
                insertpembayaran();
                showNotification("Pembayaran Berhasil", "Pembayaran telah berhasil, Terimakasih :3\nPesanan anda akan diproses.");
                  try {
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePagePelanggan.fxml"));
                     Parent root = loader.load();

                     Stage stage = (Stage) btHome.getScene().getWindow();
                     stage.setScene(new Scene(root));
                  } catch (IOException e) {
                     Logger.getLogger(BuatPesananController.class.getName()).log(Level.SEVERE, null, e);
                }
                
            } else {
                
                alert.close();
            }
        });
    }

    
    //untuk batalkan pesana maka mengpaus pesanan
    @FXML
    void hapusPesanan(ActionEvent event) {
        deletepemesananProduk();
        deletepemesanan();
        deleteOrder();
    }
    // untuk memasukkan barang dalam pesanan
    @FXML
    void insertKeranjang(ActionEvent event) {
        insertPemesananProduct();
        showOrder();
    }

    //untuk pindah scene ke lihat menu
    @FXML
    void keMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LihatMenu.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) btMenu.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            Logger.getLogger(BuatPesananController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //untuk kembali ke halaman home
    @FXML
    void keRumah(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePagePelanggan.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) btHome.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            Logger.getLogger(BuatPesananController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    //buat tambah pesanan
    @FXML
    void BuatPesan(ActionEvent event) {
        insertPesan();

    }
    
    //untuk memunculkan menu dan mendapat data kedalam choicebox
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showMenu();
        cbID.getItems().addAll(id);
    }
    
    //untuk mendapatkan data produk
    public ObservableList<Produk> getDataProduk() {
        ObservableList<Produk> produk = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT * FROM `product`";
        Statement st;
        ResultSet rs;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Produk temp;
            
                while(rs.next()) {
                    temp = new Produk(rs.getInt("id_product"), rs.getString("nama_product"), rs.getString("description"), rs.getInt("harga"));
                    produk.add(temp);
                    id.add(temp.getId_product());
                }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return produk;
    }
    
    public void showMenu() {
        ObservableList<Produk> list = getDataProduk();
        
        colID.setCellValueFactory(new PropertyValueFactory<>("id_product"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama_product"));
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<>("description"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        
        tvMenu.setItems(list);
    }
    
    public ObservableList<Order> getDataOrder() {
        ObservableList<Order> order = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT\n" +
                       "    product.nama_product AS Nama_Produk,\n" +
                       "    pemesanan_produk.Jumlah,\n" +
                       "    SUM(product.harga * pemesanan_produk.Jumlah) AS Total_Harga\n" +
                       "FROM pemesanan_produk\n" +
                       "\n" +
                       "JOIN product ON pemesanan_produk.id_product = product.id_product\n" +
                       "WHERE pemesanan_produk.Pemesanan_ID = (SELECT MAX(pemesanan.Pemesanan_ID) FROM pemesanan)\n" +
                       "GROUP BY product.nama_product, pemesanan_produk.Jumlah";
        Statement st;
        ResultSet rs;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Order temp;
            
            while(rs.next()) {
                temp = new Order(rs.getString("Nama_Produk"), rs.getInt("Jumlah"), rs.getInt("Total_Harga"));
                order.add(temp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return order;
    }
    
    public void showOrder() {
        ObservableList<Order> list = getDataOrder();
        
        colNamap.setCellValueFactory(new PropertyValueFactory<>("Nama_Produk"));
        colKuantitas.setCellValueFactory(new PropertyValueFactory<>("Jumlah"));
        colHargap.setCellValueFactory(new PropertyValueFactory<>("Total_Harga"));
        
        tvMenu1.setItems(list);
    }
    
    private void  deleteOrder() {
        ObservableList<Order> order = FXCollections.observableArrayList();
        
        tvMenu1.setItems(order);
    }

    private int pID;
     public void pemesanID(int userId) {
        String sql = "SELECT MAX(pemesanan_ID) as maxID FROM pemesanan WHERE id_pengguna = ?";
        Connection conn = DBHelper.getConnection();
    
        PreparedStatement prepare;
        ResultSet result;
        try {
            prepare = conn.prepareStatement(sql);
            prepare.setInt(1, userId); // Set the user ID parameter
            result = prepare.executeQuery();  
        
            if (result.next()) {
                int maxID = result.getInt("maxID");
                pID = maxID; // Set the pID with the maximum ID
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

  
  private void insertPemesananProduct() {
    String querySelectPemesan = "SELECT * FROM `pemesanan` WHERE `Pemesanan_ID` = ?";
    String querySelectPemesananProduk = "SELECT * FROM `pemesanan_produk` WHERE `Pemesanan_ID` = ? AND `id_product` = ?";
    String queryInsert = "INSERT INTO `pemesanan_produk`(`Pemesanan_ID`, `id_product`, `Jumlah`) VALUES (?, ?, ?)";
    String queryUpdate = "UPDATE `pemesanan_produk` SET `Jumlah` = `Jumlah` + ? WHERE `Pemesanan_ID` = ? AND `id_product` = ?";

    Connection conn = DBHelper.getConnection();

    try (PreparedStatement pstSelectPemesan = conn.prepareStatement(querySelectPemesan);
         PreparedStatement pstSelectPemesananProduk = conn.prepareStatement(querySelectPemesananProduk);
         PreparedStatement pstInsert = conn.prepareStatement(queryInsert);
         PreparedStatement pstUpdate = conn.prepareStatement(queryUpdate)) {

        
        pemesanID(getUserId());

        int pemesananID = pID;
        int idProduct = cbID.getValue();
        int jumlah = Integer.parseInt(tfKuantitas.getText());

        
        pstSelectPemesan.setInt(1, pemesananID);
        ResultSet rsPemesan = pstSelectPemesan.executeQuery();

        if (rsPemesan.next()) {
            String status = rsPemesan.getString("Status");

            if ("Complete".equals(status) || "On Delivery".equals(status)) {
                
                warn.setText("Pesanan ini sudah tidak bisa diuabh lagi silahkan buat yang baru");
                return;
            }
        }

        
        pstSelectPemesananProduk.setInt(1, pemesananID);
        pstSelectPemesananProduk.setInt(2, idProduct);
        ResultSet resultSet = pstSelectPemesananProduk.executeQuery();

        if (resultSet.next()) {
            
            pstUpdate.setInt(1, jumlah);
            pstUpdate.setInt(2, pemesananID);
            pstUpdate.setInt(3, idProduct);
            pstUpdate.executeUpdate();
        } else {
            
            pstInsert.setInt(1, pemesananID);
            pstInsert.setInt(2, idProduct);
            pstInsert.setInt(3, jumlah);
            pstInsert.executeUpdate();
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


  
public int getUserId() {
    int userId = 0;
    Connection conn = DBHelper.getConnection();
    String query = "SELECT id_pengguna FROM pelanggan WHERE user_name = ?";
    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, usename);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            userId = rs.getInt("id_pengguna");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return userId;
}

public String getUsermp(){
    String userMp="";
    Connection conn = DBHelper.getConnection();
    String query = "SELECT mpDisukai FROM pelanggan WHERE user_name = ?";
        try (PreparedStatement pst= conn.prepareStatement(query);){
            pst.setString(1, usename);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                userMp=rs.getString("mpDisukai");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuatPesananController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userMp;
}

public int getTotal() {
    int total = 0;
    Connection conn = DBHelper.getConnection();

    if (conn != null) {
        String querytotal = "SELECT\n" +
                            "    pemesanan.Pemesanan_ID,\n" +
                            "    SUM(product.harga * pemesanan_produk.Jumlah) AS Total_Harga\n" +
                            "FROM pemesanan\n" +
                            "\n" +
                            "JOIN pemesanan_produk ON pemesanan.Pemesanan_ID = pemesanan_produk.Pemesanan_ID\n" +
                            "JOIN product ON pemesanan_produk.id_product = product.id_product\n" +
                            "\n" +
                            "WHERE\n" +
                            "pemesanan.Pemesanan_ID = (SELECT MAX(pemesanan.Pemesanan_ID) FROM pemesanan)";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(querytotal)) {

            if (resultSet.next()) {
                total = resultSet.getInt("Total_Harga");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
    }

    return total;
}


 
private void insertPesan() {
    String query = "INSERT INTO `pemesanan`(`Tanggal`, `Status`, `id_pengguna`) VALUES (?, ?, ?)";
    Connection conn = DBHelper.getConnection();
    PreparedStatement pst = null;
    LocalDate today = LocalDate.now();
    Date sqlDate = Date.valueOf(today);

    try {
        pst = conn.prepareStatement(query);
        pst.setDate(1, sqlDate);
        pst.setString(2, "Incompleted");
        pst.setInt(3, getUserId());

        // Execute the statement
        pst.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(BuatPesananController.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        // Close the PreparedStatement in a finally block
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

private void deletepemesananProduk() {
        String query = "DELETE  FROM pemesanan_produk WHERE Pemesanan_ID = ?";
         Connection conn = DBHelper.getConnection();
         PreparedStatement pst;
         pemesanID(getUserId());
         int pmesanID = pID;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, pmesanID);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BuatPesananController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

private void deletepemesanan() {
    String query = "DELETE FROM pemesanan WHERE Pemesanan_ID = ?";
    Connection conn = DBHelper.getConnection();
    PreparedStatement pst;
    pemesanID(getUserId());
    int pmesanID = pID;

    try {
        pst = conn.prepareStatement(query);
        pst.setInt(1, pmesanID);
        int rowsAffected = pst.executeUpdate();

        if (rowsAffected == 0) {
            // Display a warning since no rows were deleted (record doesn't exist)
            warn.setText("Pemesanan tidak ditemukan");
        }
    } catch (SQLException ex) {
        Logger.getLogger(BuatPesananController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

private void insertpembayaran(){
    String query = "INSERT INTO `pembayaran`( `MetodePembayaran`, `Tanggal`, `JumlahPembayaran`, `Pemesanan_ID`) VALUES (?,?,?,?)";
    
    Connection conn = DBHelper.getConnection();
    PreparedStatement pst;
    pemesanID(getUserId());
    int pemesananid= pID;
    LocalDate today = LocalDate.now();
    Date sqlDate = Date.valueOf(today);
    
        try {
            pst=conn.prepareStatement(query);
            pst.setString(1, getUsermp());
            pst.setDate(2, sqlDate);
            pst.setInt(3, getTotal());
            pst.setInt(4,  pemesananid);
            
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BuatPesananController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}

private void showNotification(String title, String content) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}
        
}
  

