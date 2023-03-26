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
        zapas_txt.setText("Введіть " + Integer.toString(Zadacha.n_zadacha) +" значення для відправних точок");
        potreby_txt.setText("Введіть " + Integer.toString(Zadacha.k_zadacha) +" значення для точок отримання");
        value_txt.setText("Введіть матрицю ватростей розміром " + Integer.toString(Zadacha.n_zadacha) +" * "+Integer.toString(Zadacha.k_zadacha));
    }
    @FXML
    void exit_second(ActionEvent event) {
        Stage stage = (Stage) exit_second.getScene().getWindow();
        stage.close();

    }

    @FXML
    void answer(ActionEvent event) throws IOException {
        String input = zapas.getText();
        String[] values = input.split(",");
        int[] array = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = Integer.parseInt(values[i]);
        }
        Zadacha.set_zapas(array);
        input = potreby.getText();
        values = input.split(",");
        array = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = Integer.parseInt(values[i]);
        }
        Zadacha.set_potreby(array);

        input = value.getText(); // Отримуємо текст з текстового поля
        String[] rows = input.split("\n"); // Розділяємо текст на рядки за допомогою нового рядка
        int[][] array1 = new int[rows.length][]; // Створюємо двовимірний масив цілих чисел з необхідною кількістю рядків

        // Конвертуємо рядки у масиви цілих чисел
        for (int i = 0; i < rows.length; i++) {
            values = rows[i].split(","); // Розділяємо рядок на елементи за допомогою коми
            array1[i] = new int[values.length]; // Створюємо рядок масиву з необхідною кількістю елементів

            // Конвертуємо елементи рядка у цілі числа та заповнюємо рядок масиву
            for (int j = 0; j < values.length; j++) {
                array1[i][j] = Integer.parseInt(values[j]);
            }
        }
        int[][] array_new = new int[Zadacha.k_zadacha][Zadacha.n_zadacha];
        for (int i = 0; i < Zadacha.n_zadacha; i++) {
            for (int j = 0; j < Zadacha.k_zadacha; j++) {
                array_new[j][i] = array1[i][j];
            }
        }
        Zadacha.set_value(array_new);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Answer.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Answer");
        stage.setScene(new Scene(root1));
        stage.show();

    }
}
