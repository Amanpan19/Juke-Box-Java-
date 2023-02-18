package com.jukebox.implementation;

import com.jukebox.audiotrack.AudioPlayer;
import com.jukebox.bean.Playlist;
import com.jukebox.bean.Songs;
import com.jukebox.dao.PlaylistDAO;
import com.jukebox.dao.PlaylistDetailsDAO;
import com.jukebox.dao.SongDAO;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PlaylistOperation {

    PlaylistDAO playlistDAO = new PlaylistDAO();
    Playlist playlist = new Playlist();
    PlaylistDetailsDAO playLi = new PlaylistDetailsDAO();
    SongDAO songDAO = new SongDAO();
    Scanner input  = new Scanner(System.in);
    public void myLibrary() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        boolean flag1;
        do {

            int choose;
            String name;  // variable to take name of the Playlist.

            System.out.format("%-5s\t%-10s\t\n", "No.", "Options");
            System.out.format("%-5d\t%-10s\t\n", 1, "Create a new PlayList");
            System.out.format("%-5d\t%-10s\t\n", 2, "Delete PlayList");
            System.out.format("%-5d\t%-10s\t\n", 3, "Add songs in PlayList");
            System.out.format("%-5d\t%-10s\t\n", 4, "Existing Playlist");
            System.out.format("%-5d\t%-10s\t\n", 5, "Back to Main Menu");

            System.out.println();

            System.out.print("Enter the operation no --> ");
            int choice2 = input.nextInt();
            input.nextLine();

            switch (choice2) {
                case 1:          // Create new Playlist
                    boolean flag2 = true;
                    do {
                        System.out.print("Enter the name of new Playlist : ");
                        name = input.nextLine();
                        boolean action = playlistDAO.playlistExist(name); //getting the boolean value
                        if (action) {                                     // if true then create playlist
                            playlistDAO.createPlaylist(name);
                            flag2 = false;
                        }
                    } while (flag2);             // If false then again ask the user to provide new name.
                    flag1 = true;
                    break;

                case 2:     // Delete the playlist
                    System.out.println("Enter the Playlist id you wants to delete : ");

                    do {
                        List<Playlist> data = playlistDAO.getAllDetails();
                        if (data.size() == 0) {
                            System.out.println("No Playlist created yet! ");
                            choose = 0;   // by initializing choose=0, we are going back to the main menu.
                        } else {
                            playlistDAO.displayList(data);
                            playlist.setPlaylist_id(input.nextInt());
                           String info = playlistDAO.deletePlaylist(playlist);
                            System.out.println(info);
                            do {
                                System.out.println("Press 0 to exit & 1 to delete");

                                choose = input.nextInt();
                            }while (choose>1); // If user enters a number greater than 1,
                                               // it is again going to ask the same thing.
                        }
                    } while (choose == 1);     // If it is 0, then we are getting out of the do while.
                    flag1 = true;
                    break;

                    // Add songs in Playlist
                case 3:

                    do {
                        System.out.println("Enter the Playlist Id : ");
                        List<Playlist> playListData = playlistDAO.getAllDetails();
                        if (playListData.size() == 0) {
                            System.out.println("No playlist available");
                            choose = 0;
                        } else {
                            playlistDAO.displayList(playListData);
                            int playId = input.nextInt();

                            System.out.println("Enter the song id you wants to add : ");
                            List<Songs> songD = songDAO.getAllSongs();
                            songDAO.displaySongs(songD);
                            int songId = input.nextInt();
                            String action = playLi.addSongInPlaylist(songId, playId);
                            System.out.println(action);
                            do {
                                System.out.println("Enter 1 for add song & 0 to exit");
                                choose = input.nextInt();

                                if (choose>1) {
                                    System.out.println("Invalid Input!");
                                }
                            } while (choose>1);
                        }
                    } while (choose == 1);
                    flag1 = true;
                    break;

                    // Existing Playlist.
                case 4:

                    System.out.println("Existing PlayList");
                    List<Playlist> details = playlistDAO.getAllDetails();
                    if (details.size() == 0) {
                        System.out.println();
                        System.out.println("No Playlist exist");
                    } else {
                        playlistDAO.displayList(details);
                        System.out.println();

                        System.out.println("Enter the playList id to see the songs in playlist : ");
                        int id = input.nextInt();
                        //now, grab the song id from the songInPlaylist table and store in a list.
                        List<Integer> list2 = playLi.grabSongIdFromDetails(id);

                        // to get the song details
                        List<Songs> lis = playLi.songsInPlaylist(list2);
                        if (lis.size() != 0) {
                            songDAO.displaySongs(lis);
                            AudioPlayer audioPlayer = new AudioPlayer();
                            audioPlayer.choice(lis);

                        } else {
                            System.out.println("No song added yet!");
                        }
                    }
                    flag1 = true;
                    break;
                case 5:
                    flag1 = false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    flag1 = true;
                    break;

            }

        }
        while (flag1);
    }
}
