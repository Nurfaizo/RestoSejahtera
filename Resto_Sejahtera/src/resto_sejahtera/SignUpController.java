package resto_sejahtera;

import db.DBHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController implements Initializable {
    
    @FXML
    private Label lbAllert;

    @FXML
    private Button btBack;

    @FXML
    private Button btSignUp;

    @FXML
    private ChoiceBox<String> cbMetode;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfAlamat;

    @FXML
    private TextField tfNama;

    @FXML
    private TextField tfUsername;

    private String metode_pembayaran[] = {"Cash", "Kartu Kredit", "E-Money"};
    
    @FXML
    void gotoLogin(ActionEvent event) {
        try {
            if(event.getSource() == btBack) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
        
                Stage stage = (Stage) btBack.getScene().getWindow();
                stage.setScene(new Scene(root));
            } else if(event.getSource() == btSignUp) {
                if(tfNama.getText().isEmpty() || tfAlamat.getText().isEmpty() || cbMetode.getValue().isEmpty() || tfUsername.getText().isEmpty() || pfPassword.getText().isEmpty()) {
                    lbAllert.setText("Please Fill In All Necessary Data");
                } else {
                    insertPelanggan();
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                    Parent root = loader.load();
        
                    Stage stage = (Stage) btBack.getScene().getWindow();
                    stage.setScene(new Scene(root));
                }
            }
        } catch(IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbMetode.getItems().addAll(metode_pembayaran);
    }
    
    private void insertPelanggan() {
        String query = "INSERT INTO `pelanggan`(`nama_pengguna`, `alamat`, `mpDisukai`, `user_name`, `PASSWORD`) VALUES "
        + "('" + tfNama.getText() + "','" + tfAlamat.getText() + "','" + cbMetode.getValue() + "','" + tfUsername.getText() + "','" + pfPassword.getText() + "')";
        
        Connection conn = DBHelper.getConnection();
        Statement st;
        
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
