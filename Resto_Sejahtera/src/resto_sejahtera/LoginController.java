package resto_sejahtera;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
            lgnMsgLabel.setText("You Try to Login");
        }else{
            lgnMsgLabel.setText("Please enter Username and Password");
        }
    }

}
