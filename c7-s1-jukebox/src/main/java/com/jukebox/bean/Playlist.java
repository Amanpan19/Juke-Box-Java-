package com.jukebox.bean;

public class Playlist {
    private int playlist_id;
    private String playlist_name;

    public  Playlist(){

    }

    public Playlist(int playlist_id, String playlist_name) {
        this.playlist_id = playlist_id;
        this.playlist_name = playlist_name;
    }

    public int getPlaylist_id() {
        return playlist_id;
    }

    public void setPlaylist_id(int playlist_id) {
        this.playlist_id = playlist_id;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    @Override
    public String toString() {
        return " playlist_name='" + playlist_name ;
    }
}
