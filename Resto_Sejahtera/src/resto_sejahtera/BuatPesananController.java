package resto_sejahtera;

import db.DBHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BuatPesananController implements Initializable{

    @FXML
    private TableColumn<?, ?> Harga;

    @FXML
    private Button btBayar;

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
    private TableColumn<?, ?> colKuantitas;

    @FXML
    private TableColumn<Produk, String> colNama;
    
    @FXML
    private TableColumn<Produk, Integer> colID;

    @FXML
    private TableColumn<?, ?> colNama1;
    
    @FXML
    private ChoiceBox<Integer> cbID;

    @FXML
    private TextField tfKuantitas;

    @FXML
    private TableView<Produk> tvMenu;

    @FXML
    private TableView<?> tvMenu1;
    
    ArrayList<Integer> id = new ArrayList<>();
    
    @FXML
    void Pembayaran(ActionEvent event) {

    }

    @FXML
    void insertKeranjang(ActionEvent event) {

    }

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showMenu();
        cbID.getItems().addAll(id);
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
}


