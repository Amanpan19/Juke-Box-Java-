package com.jukebox.bean;

public class Songs {

    private int song_Id;
    private String song_Name;
    private String artist_Name;
    private String genre;
    private  String duration;
    private String filePath;

    public Songs(int song_Id, String song_Name, String artist_Name, String genre, String duration, String filePath) {
        this.song_Id = song_Id;
        this.song_Name = song_Name;
        this.artist_Name = artist_Name;
        this.genre = genre;
        this.duration = duration;
        this.filePath = filePath;
    }

    public int getSong_Id() {
        return song_Id;
    }

    public void setSong_Id(int song_Id) {
        this.song_Id = song_Id;
    }

    public String getSong_Name() {
        return song_Name;
    }

    public void setSong_Name(String song_Name) {
        this.song_Name = song_Name;
    }

    public String getArtist_Name() {
        return artist_Name;
    }

    public void setArtist_Name(String artist_Name) {
        this.artist_Name = artist_Name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Id=" + song_Id +
                ", Title ='" + song_Name + '\'' +
                ", Artist ='" + artist_Name + '\'' +
                ", Genre='" + genre + '\'' +
                ", Duration='" + duration;
    }
}
