package com.techomic.java.AudioScheduler.Playlist;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class NewPlaylistFormController {
    @FXML
    private Button btnSavePlaylist;

    public void init() {
        btnSavePlaylist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("test");
                alert.setContentText("some test");
                alert.showAndWait();
            }
        });
    }
}
