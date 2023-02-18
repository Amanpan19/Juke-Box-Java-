
import com.jukebox.bean.Songs;
import com.jukebox.dao.SongDAO;
import com.jukebox.exception.ArtistNameNotFoundException;
import com.jukebox.exception.GenreNotFoundException;
import com.jukebox.exception.SongNotFoundException;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class SongTest {

    SongDAO songDAO;
    @Before
    public void setUp(){
            songDAO = new SongDAO();
        }

    @After
    public void tearUp(){
            songDAO= null;
        }

    @Test
    public void songsSize()throws SQLException{
             // checking the song list size.
            assertEquals(5,songDAO.getAllSongs().size());

            assertNotEquals(83,songDAO.getAllSongs().size());
       }

    @Test
    public void search() throws SQLException, ArtistNameNotFoundException, GenreNotFoundException, SongNotFoundException {
            assertNotEquals(3,songDAO.searchBy(1,"Blue").size());
            assertEquals(1,songDAO.searchBy(2,"Mohit").size());
    }

    @Test
    public  void displaySong() throws SQLException {
            assertEquals("Blue Bird",songDAO.getAllSongs().get(1).getSong_Name());
            assertNotEquals("The Nights",songDAO.getAllSongs().get(3).getSong_Name());
    }



    }
