package com.example.demo5;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Error {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back;

    @FXML
    private Button Exit;

    @FXML
    void exit_all_act(ActionEvent event) {
        Stage[] stages = Stage.getWindows().toArray(new Stage[0]);

        // Close each window
        for (Stage stage : stages) {
            stage.close();
        }
    }

    @FXML
    void exit_error(ActionEvent event) {
        Stage stage = (Stage) Back.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'Error.fxml'.";
        assert Exit != null : "fx:id=\"Exit\" was not injected: check your FXML file 'Error.fxml'.";

    }

}
