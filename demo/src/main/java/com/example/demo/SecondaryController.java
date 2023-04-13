package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SecondaryController implements Initializable {

    // Blurbox Effect
    @FXML
    Pane BlurBox;

    // Game Tracker
    int gameArray[][] = new int[3][3];

    // Player Labels
    @FXML
    private Label Player1;
    @FXML
    private Label Player2;
    @FXML
    private Label CurrentTurnLabel;

    // Tic Tac Toe Board Buttons
    @FXML
    private Button OneOne;
    @FXML
    private Button OneTwo;
    @FXML
    private Button OneThree;
    @FXML
    private Button TwoOne;
    @FXML
    private Button TwoTwo;
    @FXML
    private Button TwoThree;
    @FXML
    private Button ThreeOne;
    @FXML
    private Button ThreeTwo;
    @FXML
    private Button ThreeThree;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Player1.setText(PrimaryController.PlayerOne);
        Player2.setText(PrimaryController.PlayerTwo);
        CurrentTurnLabel.setText("Player 1");
    }

    private void printGamearray() {
        int i, j;

        for (i = 0; i <= 2; i++) {
            for (j = 0; j <= 2; j++) {
                System.out.print(gameArray[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private void UpdatePlayer() {
        if (CurrentTurnLabel.getText() == "Player 2") {
            CurrentTurnLabel.setText("Player 1");
        } else if (CurrentTurnLabel.getText() == "Player 1") {
            CurrentTurnLabel.setText("Player 2");
        } else {
            System.out.println("Error");
        }
    }

    private boolean isFull() {
        int i, j;
        boolean status = true;

        for (i = 0; i <= 2; i++) {
            for (j = 0; j <= 2; j++) {
                if (gameArray[i][j] == 0) {
                    status = false;
                }
            }
        }
        return status;
    }

    private void gameIsTie() {
        BlurBox.setEffect(new BoxBlur(5, 10, 10));

        Pane Pane = new Pane();
        Pane.setStyle(
                "-fx-background-color: FDF6E4; -fx-border-color: linear-gradient(#BFEF88 50%, #FFFFB1 100%); -fx-border-width: 5;");

        Label WinnerDeclarationLabel = new Label();
        WinnerDeclarationLabel.layoutXProperty()
                .bind(Pane.widthProperty().subtract(WinnerDeclarationLabel.widthProperty()).divide(2));
        WinnerDeclarationLabel.setLayoutY(50);
        WinnerDeclarationLabel.setText("Tie!");
        WinnerDeclarationLabel.setStyle("--fx-font-family: Comic Sans MS; -fx-text-fill: black; -fx-font-size: 26px");
        Button CloseButton = new Button("Close");
        CloseButton.setPrefHeight(42);
        CloseButton.setPrefWidth(102);
        CloseButton.setLayoutX(100);
        CloseButton.setLayoutY(125);
        CloseButton.setStyle(
                "-fx-background-color:  linear-gradient(#BFEF88 0%, #FFFFB1 100%); -fx-text-fill: black; -fx-border-width: 1px; -fx-border-color: black; -fx-border-radius: 60; -fx-background-radius: 60");

        CloseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage stage = (Stage) CloseButton.getScene().getWindow();
                try {
                    App.setRoot("primary");
                    stage.close();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        });

        Pane.getChildren().add(CloseButton);
        Pane.getChildren().add(WinnerDeclarationLabel);

        Scene scene = new Scene(Pane, 300, 200);

        Stage newWindow = new Stage();
        newWindow.setScene(scene);
        newWindow.initStyle(StageStyle.UNDECORATED);
        newWindow.setResizable(false);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.show();
    }

    private String isWinner() {
        String winningplayer = "none";
        int sumRow;
        int sumCol;
        int sumAngle;

        // For each Row
        for (int i = 0; i < 3; i++) {
            sumRow = 0;
            for (int j = 0; j < 3; j++) {
                sumRow = sumRow + gameArray[i][j];
            }
            if (sumRow == -3 || sumRow == 3) {
                if (sumRow == -3) {
                    winningplayer = PrimaryController.PlayerTwo;
                } else {
                    winningplayer = PrimaryController.PlayerOne;
                }
            }
        }

        // For each Column
        for (int i = 0; i < 3; i++) {
            sumCol = 0;
            for (int j = 0; j < 3; j++) {
                sumCol = sumCol + gameArray[j][i];
            }
            if (sumCol == -3 || sumCol == 3) {
                if (sumCol == -3) {
                    winningplayer = PrimaryController.PlayerTwo;
                } else {
                    winningplayer = PrimaryController.PlayerOne;
                }
            }
        }

        // For Down Sloped Angles
        sumAngle = 0;
        for (int i = 0; i < 3; i++) {
            sumAngle = sumAngle + gameArray[i][i];
        }
        if (sumAngle == -3 || sumAngle == 3) {
            if (sumAngle == -3) {
                winningplayer = PrimaryController.PlayerTwo;
            } else {
                winningplayer = PrimaryController.PlayerOne;
            }
        }

        // For Upward Sloped Angles
        sumAngle = 0;
        sumAngle = gameArray[0][2] + gameArray[1][1] + gameArray[2][0];
        if (sumAngle == -3 || sumAngle == 3) {
            if (sumAngle == -3) {
                winningplayer = PrimaryController.PlayerTwo;
            } else {
                winningplayer = PrimaryController.PlayerOne;
            }
        }
        return winningplayer;
    }

    private void showWinnerScreen() {
        BlurBox.setEffect(new BoxBlur(5, 10, 10));

        Pane Pane = new Pane();
        Pane.setStyle(
                "-fx-background-color: FDF6E4; -fx-border-color: linear-gradient(#BFEF88 50%, #FFFFB1 100%); -fx-border-width: 5;");

        Label WinnerDeclarationLabel = new Label();
        WinnerDeclarationLabel.setLayoutX(100);
        WinnerDeclarationLabel.setLayoutY(40);
        WinnerDeclarationLabel.setText("Winner!");
        WinnerDeclarationLabel.setStyle("--fx-font-family: Comic Sans MS; -fx-text-fill: black; -fx-font-size: 26px");

        Label WinnersLabel = new Label();
        WinnersLabel.layoutXProperty().bind(Pane.widthProperty().subtract(WinnersLabel.widthProperty()).divide(2));
        WinnersLabel.setLayoutY(75);
        WinnersLabel.setText(isWinner().toString());
        WinnersLabel.setStyle("--fx-font-family: Comic Sans MS; -fx-text-fill: black; -fx-font-size: 26px");

        Button CloseButton = new Button("Close");
        CloseButton.setPrefHeight(42);
        CloseButton.setPrefWidth(102);
        CloseButton.setLayoutX(100);
        CloseButton.setLayoutY(125);
        CloseButton.setStyle(
                "-fx-background-color:  linear-gradient(#BFEF88 0%, #FFFFB1 100%); -fx-text-fill: black; -fx-border-width: 1px; -fx-border-color: black; -fx-border-radius: 60; -fx-background-radius: 60");

        CloseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage stage = (Stage) CloseButton.getScene().getWindow();
                try {
                    App.setRoot("primary");
                    stage.close();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        });

        Pane.getChildren().add(WinnersLabel);
        Pane.getChildren().add(CloseButton);
        Pane.getChildren().add(WinnerDeclarationLabel);

        Scene scene = new Scene(Pane, 300, 200);

        Stage newWindow = new Stage();
        newWindow.setScene(scene);
        newWindow.initStyle(StageStyle.UNDECORATED);
        newWindow.setResizable(false);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.show();
    }

    @FXML
    private void OneOne() throws IOException {
        if (CurrentTurnLabel.getText().equals("Player 1")) {
            OneOne.setText("X");
            OneOne.setDisable(true);
            OneOne.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[0][0] = 1;

        } else if (CurrentTurnLabel.getText().equals("Player 2")) {
            OneOne.setText("O");
            OneOne.setDisable(true);
            OneOne.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[0][0] = -1;

        } else {
            System.out.println("Error");
        }

        if (isWinner() != "none") {
            showWinnerScreen();
        } else if (isFull() == true) {
            gameIsTie();
        } else {
            UpdatePlayer();
        }
    }

    @FXML
    private void OneTwo() throws IOException {
        if (CurrentTurnLabel.getText().equals("Player 1")) {
            OneTwo.setText("X");
            OneTwo.setDisable(true);
            OneTwo.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[0][1] = 1;

        } else if (CurrentTurnLabel.getText().equals("Player 2")) {
            OneTwo.setText("O");
            OneTwo.setDisable(true);
            OneTwo.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[0][1] = -1;

        } else {
            System.out.println("Error");
        }
        if (isWinner() != "none") {
            showWinnerScreen();
        } else if (isFull() == true) {
            gameIsTie();
        } else {
            UpdatePlayer();
        }
    }

    @FXML
    private void OneThree() throws IOException {
        if (CurrentTurnLabel.getText().equals("Player 1")) {
            OneThree.setText("X");
            OneThree.setDisable(true);
            OneThree.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[0][2] = 1;

        } else if (CurrentTurnLabel.getText().equals("Player 2")) {
            OneThree.setText("O");
            OneThree.setDisable(true);
            OneThree.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[0][2] = -1;

        } else {
            System.out.println("Error");
        }
        if (isWinner() != "none") {
            showWinnerScreen();
        } else if (isFull() == true) {
            gameIsTie();
        } else {
            UpdatePlayer();
        }
    }

    @FXML
    private void TwoOne() throws IOException {
        if (CurrentTurnLabel.getText().equals("Player 1")) {
            TwoOne.setText("X");
            TwoOne.setDisable(true);
            TwoOne.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[1][0] = 1;

        } else if (CurrentTurnLabel.getText().equals("Player 2")) {
            TwoOne.setText("O");
            TwoOne.setDisable(true);
            TwoOne.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[1][0] = -1;

        } else {
            System.out.println("Error");
        }
        if (isWinner() != "none") {
            showWinnerScreen();
        } else if (isFull() == true) {
            gameIsTie();
        } else {
            UpdatePlayer();
        }
    }

    @FXML
    private void TwoTwo() throws IOException {
        if (CurrentTurnLabel.getText().equals("Player 1")) {
            TwoTwo.setText("X");
            TwoTwo.setDisable(true);
            TwoTwo.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[1][1] = 1;

        } else if (CurrentTurnLabel.getText().equals("Player 2")) {
            TwoTwo.setText("O");
            TwoTwo.setDisable(true);
            TwoTwo.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[1][1] = -1;

        } else {
            System.out.println("Error");
        }
        if (isWinner() != "none") {
            showWinnerScreen();
        } else if (isFull() == true) {
            gameIsTie();
        } else {
            UpdatePlayer();
        }
    }

    @FXML
    private void TwoThree() throws IOException {
        if (CurrentTurnLabel.getText().equals("Player 1")) {
            TwoThree.setText("X");
            TwoThree.setDisable(true);
            TwoThree.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[1][2] = 1;

        } else if (CurrentTurnLabel.getText().equals("Player 2")) {
            TwoThree.setText("O");
            TwoThree.setDisable(true);
            TwoThree.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[1][2] = -1;

        } else {
            System.out.println("Error");
        }
        if (isWinner() != "none") {
            showWinnerScreen();
        } else if (isFull() == true) {
            gameIsTie();
        } else {
            UpdatePlayer();
        }
    }

    @FXML
    private void ThreeOne() throws IOException {
        if (CurrentTurnLabel.getText().equals("Player 1")) {
            ThreeOne.setText("X");
            ThreeOne.setDisable(true);
            ThreeOne.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[2][0] = 1;

        } else if (CurrentTurnLabel.getText().equals("Player 2")) {
            ThreeOne.setText("O");
            ThreeOne.setDisable(true);
            ThreeOne.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[2][0] = -1;

        } else {
            System.out.println("Error");
        }
        if (isWinner() != "none") {
            showWinnerScreen();
        } else if (isFull() == true) {
            gameIsTie();
        } else {
            UpdatePlayer();
        }
    }

    @FXML
    private void ThreeTwo() throws IOException {
        if (CurrentTurnLabel.getText().equals("Player 1")) {
            ThreeTwo.setText("X");
            ThreeTwo.setDisable(true);
            ThreeTwo.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[2][1] = 1;

        } else if (CurrentTurnLabel.getText().equals("Player 2")) {
            ThreeTwo.setText("O");
            ThreeTwo.setDisable(true);
            ThreeTwo.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[2][1] = -1;

        } else {
            System.out.println("Error");
        }
        if (isWinner() != "none") {
            showWinnerScreen();
        } else if (isFull() == true) {
            gameIsTie();
        } else {
            UpdatePlayer();
        }
    }

    @FXML
    private void ThreeThree() throws IOException {
        if (CurrentTurnLabel.getText().equals("Player 1")) {
            ThreeThree.setText("X");
            ThreeThree.setDisable(true);
            ThreeThree.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[2][2] = 1;

        } else if (CurrentTurnLabel.getText().equals("Player 2")) {
            ThreeThree.setText("O");
            ThreeThree.setDisable(true);
            ThreeThree.setStyle("-fx-opacity:1.0; -fx-text-fill: black; -fx-background-color: transparent;");
            gameArray[2][2] = -1;

        } else {
            System.out.println("Error");
        }
        if (isWinner() != "none") {
            showWinnerScreen();
        } else if (isFull() == true) {
            gameIsTie();
        } else {
            UpdatePlayer();
        }
    }
}