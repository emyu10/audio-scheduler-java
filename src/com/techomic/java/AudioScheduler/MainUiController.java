package com.techomic.java.AudioScheduler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainUiController {
    @FXML
    private BorderPane rootPane;

    @FXML
    private Label statusText;

    @FXML
    public void initUi() {
        statusText.setText("now playing ...");
    }
}
