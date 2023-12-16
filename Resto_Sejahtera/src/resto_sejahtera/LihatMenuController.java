package resto_sejahtera;

import db.DBHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LihatMenuController implements Initializable{

    @FXML
    private Button btHome;

    @FXML
    private Button btPesan;
   
    @FXML
    private Button btBatal;
    
    @FXML
    private Button btTambah;
    
    @FXML
    private Button btBayar;
    
    @FXML
    private Button btBuat;

    @FXML
    private TableColumn<Produk, String> colDeskripsi;
    
    @FXML
    private TableColumn<?, ?> colKuantitas;

    @FXML
    private TableColumn<Produk, Integer> colHarga;
    
    @FXML
    private TableColumn<?, ?> colHargap;

    @FXML
    private TableColumn<Produk, Integer> colID;
    
    @FXML
    private TextField tfKuantitas;

    @FXML
    private TableColumn<Produk, String> colNama;
    
    @FXML
    private TableColumn<?, ?> colNamap;

    @FXML
    private TableView<Produk> tvMenu;
    
    @FXML
    private TableView<?> tvMenu1;

    @FXML
    void kePesan(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BuatPesanan.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) btPesan.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            Logger.getLogger(LihatMenuController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void keRumah(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePagePelanggan.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) btHome.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            Logger.getLogger(LihatMenuController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @FXML
    void BuatPesan(ActionEvent event) {

    }
    
     @FXML
    void hapusPesanan(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showMenu();
    }
    
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
}
