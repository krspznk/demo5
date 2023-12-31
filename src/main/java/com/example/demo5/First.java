package com.example.demo5;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class First {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button done;

    @FXML
    private Button exit;

    @FXML
    public TextField n;

    @FXML
    public TextField k;


    @FXML
    void initialize() {
    }


    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    void second(ActionEvent event) throws IOException {
        try {
            Zadacha.setK_zadacha(Integer.parseInt(k.getText()));
            Zadacha.setN_zadacha(Integer.parseInt(n.getText()));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Second.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Введіть значення");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Error.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Помилка");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

}