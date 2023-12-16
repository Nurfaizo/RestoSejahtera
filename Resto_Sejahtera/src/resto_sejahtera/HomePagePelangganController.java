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

public class HomePagePelangganController {

    @FXML
    private Button btMenu;

    @FXML
    private Button btPesanan;

    @FXML
    void BuatPesanan(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BuatPesanan.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btMenu.getScene().getWindow();
        stage.setScene(new Scene(root));
        } catch (IOException e) {
        Logger.getLogger(HomePagePelangganController.class.getName()).log(Level.SEVERE, null, e);
        System.out.println("Failed to load " + "BuatPesanan.fxml");
        }
    }

    @FXML
    void LihatMenu(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LihatMenu.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btMenu.getScene().getWindow();
        stage.setScene(new Scene(root));
        } catch (IOException e) {
        Logger.getLogger(HomePagePelangganController.class.getName()).log(Level.SEVERE, null, e);
        System.out.println("Failed to load " + "LihatMenu.fxml");
        }
    }

}
