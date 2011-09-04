/** A very thin wrapper around an ArrayList of String objects.
 *  Inherits from MediaInfo, and the inherited fields hold
 *  the album's artist and title. Each String in the TrackList
 *  represents the name of a song from the album.
 */
import java.util.ArrayList;

public class Album extends MediaInfo {
    ArrayList< String > TrackList;
    int artwork;

    public Album( String _artist, String _title ) {
        TrackList = new ArrayList< String >();
        artist = _artist;
        title = _title;
    }

    public ArrayList< String > getTrackList() {
        return TrackList;
    }

    public void addTrack( String trackName ) {
        TrackList.add( new String(trackName));
    }
}
