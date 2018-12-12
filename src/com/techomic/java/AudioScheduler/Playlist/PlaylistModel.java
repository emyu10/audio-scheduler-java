package com.techomic.java.AudioScheduler.Playlist;

import com.techomic.java.AudioScheduler.AppConstants;
import com.techomic.java.AudioScheduler.Playlist.Playlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class PlaylistModel {
    private Connection conn = null;

    private void connect() throws SQLException{
//        System.out.println(PlaylistModel.class.getResource("../storage.db").getPath());
        this.conn = DriverManager.getConnection(AppConstants.DB_URL);
    }

    public String newRecord(String playlistName, boolean cont) {
        String result = "";
        if (cont && hasContinuousPlaylist()) {
            return "EXISTS";
        }
        try {
            connect();
            String sql = "INSERT INTO playlist (playlist_name, cont) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int contInt = cont ? 1 : 0;
            stmt.setString(1, playlistName);
            stmt.setInt(2, contInt);
            stmt.executeUpdate();
            result = AppConstants.QUERY_SUCCESS;
        } catch (SQLException e) {
//            System.out.println(e.getErrorCode());
//            System.out.println(e.getSQLState());
//            System.out.print(this.conn);
            e.printStackTrace();
            result = e.getMessage();
        }
        return result;
    }

    public String newRecord(Playlist playlist) {
        return newRecord(playlist.getPlaylistName().get(), playlist.getContinuous().get());
    }

    public ObservableList<Playlist> getAll() {
        ObservableList<Playlist> playlists = FXCollections.observableArrayList();
        try {
            connect();
            String sql = "SELECT plid, playlist_name, cont, createdon FROM playlist ORDER BY createdon";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setPlaylistId(rs.getInt("plid"));
                playlist.setPlaylistName(rs.getString("playlist_name"));
                playlist.setContinuous(rs.getInt("cont"));
                playlist.setCreatedOn(rs.getInt("createdon"));
                playlists.add(playlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlists;
    }

    private boolean hasContinuousPlaylist() {
        try {
            connect();
            String sql = "SELECT * FROM playlist WHERE cont=1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            return true;
        }
    }
}
