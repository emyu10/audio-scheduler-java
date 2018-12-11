package com.techomic.java.AudioScheduler.Playlist;

import com.techomic.java.AudioScheduler.AppConstants;
import com.techomic.java.AudioScheduler.Playlist.Playlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaylistModel {
    private Connection conn = null;

    public void connect() throws SQLException{
        this.conn = DriverManager.getConnection(AppConstants.DB_URL);
    }

    public String newRecord(String playlistName, boolean cont) {
        String result = "";
        try {
            connect();
            String sql = "INSERT INTO playlist (playlist_name, cont) VALUES (?. ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int contInt = cont ? 1 : 0;
            stmt.setString(1, playlistName);
            stmt.setInt(2, contInt);
            stmt.executeUpdate();
            result = AppConstants.QUERY_SUCCESS;
        } catch (SQLException e) {
            result = e.getMessage();
        }
        return result;
    }

    public String newRecord(Playlist playlist) {
        return newRecord(playlist.getPlaylistName().get(), playlist.getContinuous().get());
    }
}
