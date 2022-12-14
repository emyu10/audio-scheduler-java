package com.techomic.java.AudioScheduler;

import com.sun.media.jfxmedia.MediaException;
import com.techomic.java.AudioScheduler.Playlist.NewPlaylistFormController;
import com.techomic.java.AudioScheduler.Playlist.Playlist;
import com.techomic.java.AudioScheduler.Playlist.PlaylistModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Callback;

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
    private AnchorPane mainContent;

    @FXML
    private Button btnNewPlaylist;

    private boolean newPlaylistFormShown = false;

    @FXML
    private TableView<Playlist> tablePlaylists;

    @FXML
    private TableColumn<Playlist, String> columnPlaylistName;

    private ObservableList<Playlist> playlists = FXCollections.observableArrayList();

    @FXML
    public void initUi() {
        statusText.setText("now playing ...");
        btnNewPlaylist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showNewPlaylistForm();
            }
        });
        columnPlaylistName.setCellValueFactory(cellData -> cellData.getValue().getPlaylistName());
        fetchPlaylists();
    }

    public void play() {
        String audioFile = "file:///home/emyu/Music/Yamaha-V50-E-Piano-1-C4.wav";
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

    @FXML
    private void showNewPlaylistForm() {
        if (!newPlaylistFormShown) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainUiController.class.getResource("Playlist/NewPlaylistForm.fxml"));
            try {
                AnchorPane form = loader.load();
                mainContent.getChildren().add(form);
                NewPlaylistFormController controller = loader.getController();
                controller.init();
                controller.setParentController(this);
                newPlaylistFormShown = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void fetchPlaylists() {
        PlaylistModel pm = new PlaylistModel();
        playlists = pm.getAll();
        tablePlaylists.setItems(playlists);
    }
}
