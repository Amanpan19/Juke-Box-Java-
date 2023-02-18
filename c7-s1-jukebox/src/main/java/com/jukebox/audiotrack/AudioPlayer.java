package com.jukebox.audiotrack;

import com.jukebox.bean.Songs;
import com.jukebox.dao.SongDAO;
import com.jukebox.util.DbUtil;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AudioPlayer {

    SongDAO songDAO=new SongDAO();
    long currentFrame;
    Clip clip;
    String status = "play";
    AudioInputStream audioPlayer ;
    static Connection connect = DbUtil.getConnection();
    String filePath = songDAO.songToPlay();



    public AudioPlayer() throws IOException, LineUnavailableException, UnsupportedAudioFileException, SQLException {
        File file = new File(filePath);
        audioPlayer = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioPlayer);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void  play(){
        clip.start();
        status = "play";
    }

    public void pause(){

        if(status.equals("paused")){
            System.out.println("Song is already paused!");
            return;
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    public void stop(){
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void shuffle(){
        try{
        Random ran = new Random();
        int Id = ran.nextInt(1,6);
        String sql = "SELECT FILEPATH FROM SONGS WHERE SONG_ID ="+Id+ ";";
        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        if(rs.next()){  // If the next song is exists
            if(clip.isRunning()){
                // then check if the song is running or not
                // If running stop that song.
                clip.stop();
                clip.close();

            }
            currentFrame = 0L;
            // Now store the filePath which we are getting from the database to a String variable filePath
            filePath = rs.getString(1);
            //To get the data from the filePath, We are using File and AudioInputStream
            File file = new File(filePath);
            audioPlayer = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();

            clip.open(audioPlayer);
            clip.start();
        }
        }catch (SQLException |UnsupportedAudioFileException |IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }

    public void resumeAudioTrack(){

        if(status.equals("play")){
            System.out.println("Audio is already being Played!");
            return;
        }
        clip.start();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }


    public void restart() throws IOException,UnsupportedAudioFileException,LineUnavailableException{
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    public void jump(long c) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (c > 0 && c < clip.getMicrosecondLength())
        {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,LineUnavailableException
    {
        audioPlayer = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip.open(audioPlayer);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void skip() throws IOException, LineUnavailableException, UnsupportedAudioFileException {

        System.out.println("Enter time (" + 0 + ", " + clip.getMicrosecondLength() + ")");
                Scanner sc = new Scanner(System.in);
                long c1 = sc.nextLong();
                jump(c1);

        }

        public void choice(List<Songs>list) throws UnsupportedAudioFileException, LineUnavailableException, IOException, SQLException {
        int choice1,next;

//            songDAO.displaySongs(list);
        Scanner input = new Scanner(System.in);
            do {

                System.out.println("Press:   1) Pause\t 2) Resume\t 3) Restart\n\t\t 4) Stop\t 5) Jump\t 6) Shuffle");

                do {
                    System.out.print("Enter the choice : ");
                    System.out.println();
                    choice1 = input.nextInt();

                    switch (choice1) {

                        case 1:
                            pause();
                            break;
                        case 2:
                            resumeAudioTrack();
                            break;
                        case 3:
                            restart();
                            break;
                        case 4:
                            stop();
                            break;
                        case 5:
                            skip();
                            break;
                        case 6:
                            shuffle();
                            break;
                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                } while (choice1 != 4);

                boolean flag;
                    do{
                        flag = false;
                    System.out.print("Enter 1 for next song & 0 to exit : ");
                    next = input.nextInt();
                    System.out.println();
                    if (next ==1 ) {
                        clip.stop();
                        clip.close();
                        currentFrame = 0L;
                        songDAO.displaySongs(list);
                        filePath = songDAO.songToPlay();
                        resetAudioStream();
                        this.play();
                    } else if (next == 0) {
                        break;
                    }
                    else {
                        System.out.println("Invalid Input!");
                        flag = true;
                          }
                    }while (flag);

            } while (next == 1);
        }

    }

