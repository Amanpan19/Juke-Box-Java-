package com.jukebox.implementation;

import com.jukebox.audiotrack.AudioPlayer;
import com.jukebox.bean.Songs;
import com.jukebox.dao.SongDAO;
import com.jukebox.exception.ArtistNameNotFoundException;
import com.jukebox.exception.GenreNotFoundException;
import com.jukebox.exception.SongNotFoundException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class JukeboxImpl {
    public static void main(String[] args) {


        int choice ;

        String enter;
        boolean flag =false;
        Scanner input = new Scanner(System.in);


        do{
            System.out.print("To start the Jukebox Press Y :  ");
            enter = input.nextLine(); // want to start jukebox, press "Y"
            if(!enter.equalsIgnoreCase("Y")){
                System.out.println("Enter "+"'Y'"+" to start! ");
            }
        }
        while (!enter.equalsIgnoreCase("Y"));

        do {

            SongDAO songDAO = new SongDAO();
            PlaylistOperation methods = new PlaylistOperation();

            try{
                System.out.println();

                System.out.println("       Welcome to Jukebox  ");
                System.out.println("----------------------------------");
                System.out.println();

                System.out.println("Song details");
                System.out.println("----------------------------------");
                System.out.println();

                List<Songs>list = songDAO.getAllSongs();
                songDAO.displaySongs(list); //Displaying songs List
                System.out.println();
                System.out.println("1) Play song");
                System.out.println("2) My Library");
                System.out.println("3) Search Song ");
                System.out.println("4) Exit ");

                System.out.println();
                do{
                System.out.print("Choose the Option : "); //Choose 1,2,3 to perform specific task.
                choice = input.nextInt();                 //If option no. not matched then again asking the user to input choice.
                if(choice>4){
                    System.out.println("Invalid Input!");
                    }
                }
                while (choice>4);

                    switch (choice) {

//  Case 1 for Audio Player or To Play song.

                        case 1:
                            AudioPlayer audioPlayer = new AudioPlayer();
                            audioPlayer.choice(list);
                            flag = true;
                            break;

//  Case 2 to give Operation Options to User

                        case 2:
                            methods.myLibrary();
                            flag = true;
                            break;

//  Case 3 to search

                        case 3:

                            int choice5;
                            int press = 0;
                            boolean flag3 ;

                            do {

                                try {
                                    do {
                                        System.out.println("Search song");
                                        System.out.println("Press 1)--> Search By Song Name");
                                        System.out.println("      2)--> Search By Artist Name");
                                        System.out.println("      3)--> Search By Genre");

                                        System.out.println();
                                        System.out.print("Enter the Category No. By which you wants to search : ");

                                        try {
                                            press = input.nextInt();
                                            System.out.println();
                                            if (press > 3) {
                                                System.out.println("Invalid input !");
                                                flag3 = true;
                                            } else {
                                                flag3 = false;
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input. Please enter a number.");
                                            input.nextLine();
                                            flag3 = true;
                                        }
                                    } while (flag3);

                                    System.out.print("Enter the Name : ");
                                    input.nextLine();
                                    String name = input.nextLine();
                                    List<Songs> songData = songDAO.searchBy(press, name);
                                    songDAO.displaySongs(songData);
                                } catch (ArtistNameNotFoundException | SongNotFoundException |
                                         GenreNotFoundException | InputMismatchException e) {
                                    System.out.println(e);
                                }
                                System.out.println("Want to search again ? 1) Search  0) Exit");
                                choice5 = input.nextInt();

                            } while (choice5 == 1);
                            flag = true;
                            break;
// Case 4 for exit
                        case 4:
                            System.out.println("Visit us again!");
                            flag = false;
                            break;
                        default:
                            System.out.println("Invalid Input!");
                            break;
                    }
            }catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException e){
                System.out.println(e);
            }

        }while (flag);
    }
}
