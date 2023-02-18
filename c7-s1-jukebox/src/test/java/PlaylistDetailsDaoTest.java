import com.jukebox.bean.Playlist;
import com.jukebox.dao.PlaylistDetailsDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;


public class PlaylistDetailsDaoTest {
    Playlist playlist;

    PlaylistDetailsDAO playlistDetailsDAO;

    @Before
    public void setUp(){
        playlistDetailsDAO = new PlaylistDetailsDAO();
        playlist = new Playlist();
    }
    @After
    public void tearDown(){
        playlistDetailsDAO = new PlaylistDetailsDAO();
        playlist=null;
    }


    @Test
    public void getSong() throws SQLException{
        List<Integer> list = playlistDetailsDAO.grabSongIdFromDetails(28);
        // Calling grabSongIdFromDetails to grab the songs id in playlist id 28.

        //By using get() method we are calling 0 index song's Song name.
        assertEquals("The Nights",playlistDetailsDAO.songsInPlaylist(list).get(0).getSong_Name());
    }


}
