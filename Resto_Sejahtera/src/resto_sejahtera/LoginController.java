package resto_sejahtera;

import db.DBHelper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button btCancel;

    @FXML
    private Button btLogin;
    
    @FXML
    private Button btSignUp;
    
    @FXML
    private Label lgnMsgLabel;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfUsename;

    @FXML
    void BackToPilih(ActionEvent event) {
        Stage stage = (Stage) btCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Login(ActionEvent event) {
        
        
        if(tfUsename.getText().isEmpty()==false && pfPassword.getText().isEmpty()==false){
//            lgnMsgLabel.setText("You Try to Login");
                isValidLogin();
        }else{
            lgnMsgLabel.setText("Please enter Username and Password");
        }
    }
    
    @FXML
    void SignUp(ActionEvent event) {
        
    }
    
    public void isValidLogin() {
        String sqlPelanggan = "SELECT 'pelanggan' as source FROM pelanggan WHERE user_name = ? AND PASSWORD = ?";
        String sqlPegawai = "SELECT 'pegawai' as source FROM pegawai WHERE user_name = ? AND PASSWORD = ?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement pstmtPelanggan = conn.prepareStatement(sqlPelanggan);
             PreparedStatement pstmtPegawai = conn.prepareStatement(sqlPegawai)) {

            pstmtPelanggan.setString(1, tfUsename.getText());
            pstmtPelanggan.setString(2, pfPassword.getText());
            ResultSet rsPelanggan = pstmtPelanggan.executeQuery();

            pstmtPegawai.setString(1, tfUsename.getText());
            pstmtPegawai.setString(2, pfPassword.getText());
            ResultSet rsPegawai = pstmtPegawai.executeQuery();

            if (rsPelanggan.next()) {
                loadFXML("HomePagePelanggan.fxml");
            } else if (rsPegawai.next()) {
                loadFXML("LihatMenu.fxml");
            } else {
                lgnMsgLabel.setText("Invalid credentials");
            }
        } catch (SQLException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            lgnMsgLabel.setText("You Try to Login");
        }
    }

    private void loadFXML(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) btLogin.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Failed to load " + fxmlFile);
        }
    }
}
