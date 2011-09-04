/**
 *  A simple structure used to represent a song.
 */
import java.io.Serializable;

public class Song extends MediaInfo implements Serializable {
    String album;

    public Song( String _title, String _artist ) {
        super();
        title = _title;
        artist = _artist;
    }

    public Song( String _title, String _artist, String _album ) {
        title = _title;
        artist = _artist;
        album = _album;
    }

    public String toString() {
        return artist + " - " + title;
    }
        
}
