package resto_sejahtera;

import db.DBHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ProsesPesananController implements Initializable{

    @FXML
    private Button btKelolaMenu;

    @FXML
    private Button btLogOut;

    @FXML
    private Button btUpdate;

    @FXML
    private TableColumn<Pemesanan, Integer> colID;

    @FXML
    private TableColumn<Pemesanan, String> colNama;

    @FXML
    private TableColumn<Pemesanan, String> colStatus;

    @FXML
    private TableColumn<Pemesanan, LocalDate> colTanggal;

    @FXML
    private TableColumn<Pemesanan, Integer> colTotalHarga;

    @FXML
    private TextField tfID;

    @FXML
    private TableView<Pemesanan> tvPesanan;

    @FXML
    void gotoKelolaMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KelolaMenu.fxml"));
            Parent root = loader.load();
        
            Stage stage = (Stage) btKelolaMenu.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch(IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

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
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btUpdate) {
            updateRecord();
        }
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        showPemesanan();
        
        tvPesanan.setOnMouseClicked(event -> {
            // Check if the click event is a single click on a row
            if (event.getClickCount() == 1 && !tvPesanan.getSelectionModel().isEmpty()) {
                // Get the selected item (row) from the TableView
                Pemesanan selectedProduk = tvPesanan.getSelectionModel().getSelectedItem();

                // Display the selected item's details in the text fields
                tfID.setText(String.valueOf(selectedProduk.getPemesanan_ID()));
            }
        });
    }
    
    public ObservableList<Pemesanan> getDataPemesanan() {
        ObservableList<Pemesanan> pesanan = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT\n" +
                       "    pemesanan.Pemesanan_ID,\n" +
                       "    pelanggan.nama_pengguna AS Nama_Pelanggan,\n" +
                       "    pemesanan.Tanggal,\n" +
                       "    SUM(product.harga * pemesanan_produk.Jumlah) AS Total_Harga,\n" +
                       "    pemesanan.Status\n" +
                       "FROM pemesanan\n" +
                       "\n" +
                       "JOIN pelanggan ON pemesanan.id_pengguna = pelanggan.id_pengguna\n" +
                       "JOIN pemesanan_produk ON pemesanan.Pemesanan_ID = pemesanan_produk.Pemesanan_ID\n" +
                       "JOIN product ON pemesanan_produk.id_product = product.id_product\n" +
                       "GROUP BY pemesanan.Pemesanan_ID, pelanggan.nama_pengguna, pemesanan.Tanggal, pemesanan.Status\n" + 
                       "ORDER BY pemesanan.Status DESC";
        Statement st;
        ResultSet rs;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Pemesanan temp;
            
            while(rs.next()) {
                temp = new Pemesanan(rs.getInt("Pemesanan_ID"), rs.getString("Nama_Pelanggan"), 
                        LocalDate.parse(rs.getString("Tanggal")), rs.getInt("Total_Harga"), rs.getString("Status"));
                pesanan.add(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return pesanan;
    }

    public void showPemesanan() {
        ObservableList<Pemesanan> list = getDataPemesanan();
        
        colID.setCellValueFactory(new PropertyValueFactory<>("Pemesanan_ID"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("Nama_Pelanggan"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("Tanggal"));
        colTotalHarga.setCellValueFactory(new PropertyValueFactory<>("Total_Harga"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        
        tvPesanan.setItems(list);
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
    
    private void updateRecord() {
        String query = "UPDATE `pemesanan` SET `Status`='Completed' WHERE pemesanan.Pemesanan_ID ='" + tfID.getText() + "'";
        update(query);
        showPemesanan();
        
        tfID.clear();
    }
}
