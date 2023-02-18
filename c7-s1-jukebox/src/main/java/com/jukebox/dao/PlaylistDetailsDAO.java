package com.jukebox.dao;

import com.jukebox.bean.PlaylistDetails;
import com.jukebox.bean.Songs;
import com.jukebox.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlaylistDetailsDAO {

    private static Connection connection = DbUtil.getConnection();
    PlaylistDetails playlistDetails = new PlaylistDetails();

    public String addSongInPlaylist(int songId, int playId) throws SQLException {
        playlistDetails.setSong_Id(songId);
        playlistDetails.setPlayList_Id(playId);
        int rows;
        String sql = "insert into songInPlaylist(songId,playlistId) values(?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,playlistDetails.getSong_Id());
        stmt.setInt(2,playlistDetails.getPlayList_Id());
        rows = stmt.executeUpdate();
        if(rows>0)
            return "Added successfully! ";

        else
            return "Song not Added !";

    }

    // To check the song in song Playlist
    public List<Songs> songsInPlaylist(List<Integer>list)throws SQLException{
        List<Songs> li = new ArrayList<>();
        Iterator<Integer> itr = list.iterator();
        String sql;
        while (itr.hasNext()){
           int id = itr.next();
            sql = "SELECT * FROM songs WHERE song_id ="+id+";";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                int songId = rs.getInt(1);
                String songName = rs.getString(2);
                String artistName = rs.getString(3);
                String genre = rs.getString(4);
                String duration = rs.getString(5);
                String fileP = rs.getString(6);
                Songs songs = new Songs(songId,songName,artistName,genre,duration,fileP);
                li.add(songs);
            }
        }

        return li;
    }

    // Here we are getting the Song id from the song In Playlist table.
    public List<Integer> grabSongIdFromDetails(int playId) throws SQLException {
        List<Integer> lis = new ArrayList<>();
        int songId;

        String sql = "SELECT songId FROM songInPlaylist WHERE playlistId = "+playId+";";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            songId = rs.getInt(1);
            lis.add(songId);
        }
        return lis;
    }
}
