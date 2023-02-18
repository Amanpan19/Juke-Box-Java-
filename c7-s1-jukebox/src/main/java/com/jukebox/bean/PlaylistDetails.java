package com.jukebox.bean;

public class PlaylistDetails {

    private int song_Id;
    private int playList_Id;

    public PlaylistDetails() {

    }

    public int getSong_Id() {
        return song_Id;
    }

    public void setSong_Id(int song_Id) {
        this.song_Id = song_Id;
    }

    public int getPlayList_Id() {
        return playList_Id;
    }

    public void setPlayList_Id(int playList_Id) {
        this.playList_Id = playList_Id;
    }

    @Override
    public String toString() {
        return "PlaylistDetails{" +
                "song_Id=" + song_Id +
                ", playList_Id=" + playList_Id +
                '}';
    }
}
