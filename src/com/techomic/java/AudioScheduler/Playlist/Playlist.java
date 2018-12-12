package com.techomic.java.AudioScheduler.Playlist;

import com.techomic.java.AudioScheduler.AppConstants;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Playlist {
    private SimpleIntegerProperty playlistId = new SimpleIntegerProperty();
    private SimpleStringProperty playlistName = new SimpleStringProperty();
    private SimpleBooleanProperty continuous = new SimpleBooleanProperty();
    private SimpleIntegerProperty createdOn = new SimpleIntegerProperty();

    public void setPlaylistId(int playlistId) {
        this.playlistId.set(playlistId);
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName.set(playlistName);
    }

    public void setContinuous(int cont) {
        if (cont == 0) {
            this.continuous.set(false);
        } else {
            this.continuous.set(true);
        }
    }

    public void setCreatedOn(int createdOn) {
        this.createdOn.set(createdOn);
    }

    public SimpleIntegerProperty getPlaylistId() {
        return this.playlistId;
    }

    public SimpleStringProperty getPlaylistName() {
        return this.playlistName;
    }

    public SimpleBooleanProperty getContinuous() {
        return this.continuous;
    }

    public SimpleIntegerProperty getCreatedOn() {
        return this.createdOn;
    }
}
