package com.jukebox.exception;

public class SongNotFoundException extends Exception{
    public SongNotFoundException(String message){
        super(message);
    }
}
