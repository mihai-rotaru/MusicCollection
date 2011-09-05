/**
 *  A very simple structure; <code>Song</code> and <code>MusicCollection</code> inherit
 *  from it.
 */
import java.io.Serializable;

public class MediaInfo implements Serializable {
    public String artist;
    public String title;

    public void setArtist( String _artist ) {
        artist = _artist;
    }

    public void setTitle( String _title ) {
        title = _title;
    }

    public String getArtist( String _artist ) {
        return artist;
    }

    public String getTitle( String _title ) {
        return title;
    }

}
