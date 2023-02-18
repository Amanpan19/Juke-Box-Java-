import com.jukebox.bean.Playlist;
import com.jukebox.bean.Songs;
import com.jukebox.dao.PlaylistDAO;
import static org.junit.Assert.*;

import com.jukebox.dao.PlaylistDetailsDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class PlaylistDAOTest {
    Playlist playlist;
    PlaylistDAO playlistDAO;

    PlaylistDetailsDAO playlistDetailsDAO;


    @Before
    public void setUp(){
        playlistDetailsDAO = new PlaylistDetailsDAO();
        playlistDAO = new PlaylistDAO();
        playlist = new Playlist();

    }
    @After
    public void tearDown(){
        playlistDetailsDAO = null;
        playlistDAO = null;
        playlist=null;

    }

    @Test
    public void songsInPlayList() throws SQLException {
        // calling a grabSongIdFromDetails method and return Integer type list.(Containing song id)
        List<Integer> li = playlistDetailsDAO.grabSongIdFromDetails(28);

        //Now, calling the songInPlaylist method which return a List of Songs type.
        List<Songs>list  = playlistDetailsDAO.songsInPlaylist(li);
        assertEquals(4,list.size());

    }

    @Test
    public void displayTest() throws SQLException {
        List<Playlist>list = playlistDAO.getAllDetails();
        assertEquals(3,list.size());  // If you increment the playlist size in the impl section then u have to increase the
                                      // expected output as well.
        assertEquals("aman",list.get(0).getPlaylist_name());
    }


    @Test
    public void existedPlaylistTest() throws SQLException {
        boolean flag = playlistDAO.playlistExist("aman");
        assertFalse(flag);
        // test case passed then it means that playlist already exist.
    }
}
