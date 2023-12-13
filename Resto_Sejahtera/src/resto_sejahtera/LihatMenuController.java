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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LihatMenuController implements Initializable{

    @FXML
    private Button btHome;

    @FXML
    private Button btPesan;

    @FXML
    private TableColumn<Produk, String> colDeskripsi;

    @FXML
    private TableColumn<Produk, Integer> colHarga;

    @FXML
    private TableColumn<Produk, Integer> colID;

    @FXML
    private TableColumn<Produk, String> colNama;

    @FXML
    private TableView<Produk> tvMenu;

    @FXML
    void kePesan(ActionEvent event) {

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showMenu();
    }
    
    public ObservableList<Produk> getDataProduk() {
        ObservableList<Produk> produk = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT * FROM `produk`";
        Statement st;
        ResultSet rs;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Produk temp;
            
            while(rs.next()) {
                temp = new Produk(rs.getInt("ID"), rs.getString("nama"), rs.getString("deskripsi"), rs.getInt("harga"));
                produk.add(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return produk;
    }
    
    public void showMenu() {
        ObservableList<Produk> list = getDataProduk();
        
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        
        tvMenu.setItems(list);
    }
}
