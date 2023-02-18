package com.jukebox.dao;

import com.jukebox.bean.Playlist;
import com.jukebox.bean.PlaylistDetails;
import com.jukebox.bean.Songs;
import com.jukebox.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaylistDAO {

    private static final Connection connect = DbUtil.getConnection();
    PlaylistDetailsDAO playLi = new PlaylistDetailsDAO();
    PlaylistDetails playlistD = new PlaylistDetails();
    SongDAO songDAO = new SongDAO();
    static Scanner input = new Scanner(System.in);


    // This method will check whether the Playlist exist or not and return a boolean value
    public boolean playlistExist(String name) throws SQLException {

        String sql = "select * from playlist where playlist_name ='"+name+"';";
        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            System.out.println("Playlist already exist!");

            return false;
        }
        else {
            // if playlist doesn't exist.
            return true;
        }

    }
    public void createPlaylist(String name)throws SQLException{
        int row, choice3;

            String sql = "INSERT INTO PLAYLIST (PLAYLIST_NAME) Values (?)";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setString(1, name);
            row = stmt.executeUpdate();
            if(row>0) {
                System.out.println("PlayList created Successfully");
                System.out.println();

                do {
                    System.out.println("Enter 1 to add song & 0 to exit");
                    choice3 = input.nextInt();
                    if (choice3 == 1) {
                        System.out.println("Enter the Song Id you wants to add : ");
                        List<Songs>list = songDAO.getAllSongs();
                        songDAO.displaySongs(list);
                        playlistD.setSong_Id(input.nextInt());

                        System.out.println("Enter the PlayList id : ");
                        List<Playlist> l_Data = getAllDetails();
                        displayList(l_Data);
                        playlistD.setPlayList_Id(input.nextInt());

                        String action = playLi.addSongInPlaylist(playlistD.getSong_Id(), playlistD.getPlayList_Id());
                        System.out.println(action);
                    }
                }
                while (choice3!=0);

        }

    }

    public String deletePlaylist(Playlist playlist) throws SQLException{
        int rows, row ;
        String sql = "Delete from songInPlaylist where PlaylistId ="+playlist.getPlaylist_id()+";";
        Statement stmt = connect.createStatement();
        rows = stmt.executeUpdate(sql);
        if(rows>0 || rows==0){  // if rows==0, means that there is no playlist available in songInPlaylist

            String sql1 = "Delete from playlist where Playlist_Id ="+playlist.getPlaylist_id()+";";
            Statement stmt1 = connect.createStatement();
            row = stmt1.executeUpdate(sql1);
            if(row>0){
                return "Playlist deleted!";
            }

        }
        return "playlist not deleted!";
    }

    // To get the details of the PlayList Table
    public List<Playlist> getAllDetails() throws SQLException{

        List<Playlist>list = new ArrayList<>();
        String sql = "SELECT * FROM PLAYLIST";
        //Statement object is used to execute SQL statement
        Statement stmt = connect.createStatement();

        //ResultSet returns a table representing database.
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()){
            int playList_id = rs.getInt(1);
            String playlist_Name = rs.getString(2);

            //Now Playlist object is calling the Parameterized Constructor and storing the values in the list
            Playlist playLi = new Playlist(playList_id,playlist_Name);
            list.add(playLi);
        }
        return list;

    }

    public void displayList(List<Playlist> listDisplay){
        System.out.println("+--------------------------+");
        System.out.format("|\t%-5s|\t%-15s|\t\n","Id","PlayList Name");
        System.out.println("+--------------------------+");
        for(Playlist list : listDisplay){
            System.out.format("|\t%-5s|\t%-15s|\t\n",list.getPlaylist_id(),list.getPlaylist_name());
        }
        System.out.println("+--------------------------+");
    }
}
