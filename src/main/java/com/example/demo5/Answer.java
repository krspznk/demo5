package com.example.demo5;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.*;


public class Answer {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label answer;

    @FXML
    private Button exit_all;


    @FXML
    void exit_all_act(ActionEvent event) {
        // Get all open windows
        Stage[] stages = Stage.getWindows().toArray(new Stage[0]);

        // Close each window
        for (Stage stage : stages) {
            stage.close();
        }
    }
    @FXML
    void initialize() {
        String lines =Zadacha.final_step();
        print_answer(lines);

    }


    public void print_answer(String lines){
        StringBuilder sb = new StringBuilder();
        sb.append(lines).append("\n");

        // Задаємо відповідний текст Label
        this.answer.setText(sb.toString());
    }
}
