package com.example.demo;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private Button closeGameButton;

    @FXML
    private TextField Player1;
    @FXML
    private TextField Player2;

    static Players PlayerList;
    public static String PlayerOne;
    public static String PlayerTwo;

    @FXML
    private void switchToSecondary() throws IOException {

        PlayerList = new Players(Player1.getText().trim(), Player2.getText().trim());
        PlayerOne = PlayerList.getplayerOne();
        PlayerTwo = PlayerList.getplayerTwo();
        App.setRoot("secondary");
    }

    @FXML
    private void closeGame() throws IOException {
        Stage stage = (Stage) closeGameButton.getScene().getWindow();
        stage.close();
    }
    

}
