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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class KelolaMenuController implements Initializable{

    @FXML
    private Button btHapus;

    @FXML
    private Button btLogOut;

    @FXML
    private Button btProsesMakanan;

    @FXML
    private Button btTambah;

    @FXML
    private Button btUpdate;

    @FXML
    private TableColumn<Produk, String> colDeskripsi;

    @FXML
    private TableColumn<Produk, Integer> colHarga;

    @FXML
    private TableColumn<Produk, Integer> colID;

    @FXML
    private TableColumn<Produk, String> colNama;

    @FXML
    private TextField tfDeskripsi;

    @FXML
    private TextField tfHarga;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfNama;

    @FXML
    private TableView<Produk> tvMenu;
    
    @FXML
    private Label lbAllert;

    @FXML
    void gotoLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
        
            Stage stage = (Stage) btLogOut.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch(IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void gotoProsesMakanan(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProsesPesanan.fxml"));
            Parent root = loader.load();
        
            Stage stage = (Stage) btProsesMakanan.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch(IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btTambah) {
            if(tfID.getText().isEmpty() || tfNama.getText().isEmpty() || tfDeskripsi.getText().isEmpty() || tfHarga.getText().isEmpty()) {
                lbAllert.setText("Please Fill In All Necessary Data");
            } else {
                insertRecord();
                lbAllert.setText("");
            }
        } else if(event.getSource() == btUpdate) {
            updateRecord();
            lbAllert.setText("");
        } else if(event.getSource() == btHapus) {
            deleteRecord();
            lbAllert.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showMenu();
        
        tvMenu.setOnMouseClicked(event -> {
            // Check if the click event is a single click on a row
            if (event.getClickCount() == 1 && !tvMenu.getSelectionModel().isEmpty()) {
                // Get the selected item (row) from the TableView
                Produk selectedProduk = tvMenu.getSelectionModel().getSelectedItem();

                // Display the selected item's details in the text fields
                tfID.setText(String.valueOf(selectedProduk.getId_product()));
                tfNama.setText(selectedProduk.getNama_product());
                tfDeskripsi.setText(selectedProduk.getDescription());
                tfHarga.setText(String.valueOf(selectedProduk.getHarga()));
            }
        });
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
    
    private void update(String query) {
        Connection conn = DBHelper.getConnection();
        Statement st;
        
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void insertRecord() {
        String query = "INSERT INTO `product` VALUES ('" + tfID.getText() + "','" + tfNama.getText() + "','" + tfDeskripsi.getText() + "','" + tfHarga.getText() + "')";
        update(query);
        showMenu();
        
        tfID.clear();
        tfNama.clear();
        tfDeskripsi.clear();
        tfHarga.clear();
    }
    
    private void updateRecord() {
        String query = "UPDATE `product` SET `nama_product`='" + tfNama.getText() + "',`description`='" + tfDeskripsi.getText() + "',`harga`='" + tfHarga.getText() + "' WHERE `id_product`='" + tfID.getText() + "'";
        update(query);
        showMenu();
        
        tfID.clear();
        tfNama.clear();
        tfDeskripsi.clear();
        tfHarga.clear();
    }
    
    private void deleteRecord() {
        String query = "DELETE FROM `product` WHERE `id_product`='" + tfID.getText() + "'";
        update(query);
        showMenu();
        
        tfID.clear();
    }
}
