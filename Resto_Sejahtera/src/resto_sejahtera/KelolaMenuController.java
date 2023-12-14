package resto_sejahtera;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class KelolaMenuController {

    @FXML
    private Button bthapus;

    @FXML
    private Button btkelolamenu;

    @FXML
    private Button bttambah;

    @FXML
    private Button btupdate;

    @FXML
    private TableColumn<?, ?> koldeskripsi;

    @FXML
    private TableColumn<?, ?> kolharga;

    @FXML
    private TableColumn<?, ?> kolid;

    @FXML
    private TableColumn<?, ?> kolnama;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfdeskripsi;

    @FXML
    private TextField tfharga;

    @FXML
    private TextField tfnama;

    @FXML
    private TableView<?> tvdata;

    @FXML
    void handleButtonAction(ActionEvent event) {

    }

}

