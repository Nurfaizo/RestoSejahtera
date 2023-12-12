package resto_sejahtera;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LihatMenuController{

    @FXML
    private Button btHome;

    @FXML
    private Button btMenu;

    @FXML
    private Button btPesan;

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
        System.out.println("Failed to load " + "HomePagePelanggan.fxml");
        }
    }

    @FXML
    void menu(ActionEvent event) {

    }

}
