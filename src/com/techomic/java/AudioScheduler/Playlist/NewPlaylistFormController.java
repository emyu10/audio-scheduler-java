package com.techomic.java.AudioScheduler.Playlist;

import com.techomic.java.AudioScheduler.MainUiController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class NewPlaylistFormController {
    private MainUiController parentController;

    @FXML
    private TextField txtPlaylistName;

    @FXML
    private CheckBox chkContinuous;

    @FXML
    private Button btnSavePlaylist;

    public void setParentController(MainUiController controller) {
        this.parentController = controller;
    }

    public void init() {
        btnSavePlaylist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                save();
            }
        });
    }

    public void save() {
        String playlistName = txtPlaylistName.getCharacters().toString();
        boolean cont = chkContinuous.isSelected();

        if (playlistName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing Values");
            alert.setHeaderText("Missing Values");
            alert.setContentText("Please enter the name of the playlist.");
            alert.showAndWait();
        } else {
            PlaylistModel playlistModel = new PlaylistModel();
            String result = playlistModel.newRecord(playlistName, cont);
            if (result.equals("QUERY_SUCCESS")) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Success");
//                alert.setHeaderText("Success");
//                alert.setContentText("Playlist added.");
//                alert.showAndWait();
                parentController.fetchPlaylists();
                txtPlaylistName.clear();
                chkContinuous.setSelected(false);
            } else if (result.equals("EXISTS")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("There already is a continuous playlist.\nYou can add only one continuous playlist.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Could not add playlist.");
                alert.showAndWait();
            }
        }
    }
}
