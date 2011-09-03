import java.util.ArrayList;

/**
 *  Represents a music collection. Is actually an aggregate
 *  of <code>Song</code> objects, with the additional capacity of adding
 *  whole <code>Album</code> objects. <code>MusicCollection</code> objects hold no
 *  <code>Album</code> objects, but instead, the tracks composing the
 *  album are added one by one, after building a <code>Song</code> object from each of
 *  them.
 */

public class MusicCollection {
    ArrayList< Song > items;

    public void addSong( Song s ) {
    }


    /** 
     *  All songs from an album are added to the collection
     *  @param album the album which will be added to the collection
     */
    public void addAlbum( Album a ) {

    }
}
