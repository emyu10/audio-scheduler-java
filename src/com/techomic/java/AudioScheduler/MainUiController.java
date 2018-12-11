package com.techomic.java.AudioScheduler;

import com.sun.media.jfxmedia.MediaException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainUiController {
    @FXML
    private BorderPane rootPane;

    @FXML
    private Label statusText;

    @FXML
    private Button btnPlay;

    @FXML
    public void initUi() {
        statusText.setText("now playing ...");
        btnPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                play();
            }
        });
    }

    public void play() {
        String audioFile = "file:///home/emyu/Music/Yamaha-V50-E-Piano-1-C4.wav";
        audioFile = "file:///home/emyu/Music/hiyy_rohva_nulaa.mp3";
        try {
            File file = new File(new URI(audioFile));
            Media m = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(m);
            mediaPlayer.play();
        } catch (URISyntaxException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File Error");
            alert.setHeaderText("Could not find file");
            alert.setContentText("The specified file could not be found.");
            alert.showAndWait();
        } catch (MediaException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Media Error");
            alert.setHeaderText("Unsupported");
            alert.setContentText("Unsupported file format.");
            alert.showAndWait();
        } catch (javafx.scene.media.MediaException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Media Error");
            alert.setHeaderText(e.getType().toString());
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}