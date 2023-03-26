package com.example.demo5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class Answer {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label answer;

    @FXML
    void initialize() {
        String  [] lines =Zadacha.final_step(Zadacha.value, Zadacha.potreby,Zadacha.zapas);
        print_answer(lines);
    }


    public void print_answer(String [] lines){
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append("    ").append(line).append("\n");
        }

        // Задаємо відповідний текст Label
        this.answer.setText(sb.toString());
    }
}
