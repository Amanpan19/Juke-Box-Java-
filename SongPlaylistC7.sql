create database JukeBox;
use jukebox;
create table Songs(
Song_Id int auto_increment primary key,
Song_name varchar(30) not null,
Artist_Name varchar(30) not null,
Gener varchar(20),
Duration varchar(6),
FilePath varchar(100) not null);

insert into Songs(Song_name, Artist_Name, Gener,Duration,FilePath)
 values('Scam 1992','Achnit Thakkar','Indian Pop','00:30',"C:\\Users\\AMAN\\Downloads\\Scam 1992 Ringtone.wav"),
       ('Blue Bird','Ikimono-gakari','Japanese Rock',"03:33","C:\\Users\\AMAN\\Downloads\\blue-Bird-naruto.wav"),
       ("The Nights","Avicii","Folktronica","02:53","C:\\Users\\AMAN\\Downloads\\Avicii-The-Nights.wav"),
       ("Deva Deva",'Arijit Singh','Indian Pop','04:39',"C:\\Users\\AMAN\\Downloads\\Deva Deva.wav"),
	   ('Phir se Ud Chala','Mohit Chauhan','Indian Pop','04:36',"C:\\Users\\AMAN\\Downloads\\Phir-Se-Ud-Chala.wav");

create table Playlist(
Playlist_Id int auto_increment primary key,
Playlist_Name varchar(25)
);

create table SongInPlaylist (
SongId int,foreign key (SongId) references Songs (Song_Id),
PlayListId int, foreign key (PlayListId) references Playlist (Playlist_Id)
);