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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Second {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button count;

    @FXML
    private Button exit_second;

    @FXML
    private TextField potreby;

    @FXML
    private Label potreby_txt;

    @FXML
    private TextArea value;

    @FXML
    private Label value_txt;

    @FXML
    private TextField zapas;

    @FXML
    private Label zapas_txt;


    @FXML
    void initialize() {
        zapas_txt.setText("Введіть " + Integer.toString(Zadacha.n_zadacha) +" значення \nдля відправних точок");
        potreby_txt.setText("Введіть " + Integer.toString(Zadacha.k_zadacha) +" значення \nдля точок отримання");
        value_txt.setText("Введіть матрицю ватростей \nрозміром " + Integer.toString(Zadacha.n_zadacha) +" * "+Integer.toString(Zadacha.k_zadacha));
    }
    @FXML
    void exit_second(ActionEvent event) {
        Stage stage = (Stage) exit_second.getScene().getWindow();
        stage.close();

    }

    @FXML
    void answer(ActionEvent event) throws IOException {
        try {
            Zadacha.set_zapas(zapas.getText());
            Zadacha.set_potreby(potreby.getText());
            Zadacha.set_vartist(value.getText());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Answer.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Відповідь");
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
    }}

}
