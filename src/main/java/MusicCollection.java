import java.util.ArrayList;
import java.io.Serializable;
import javax.swing.*;

/**
 *  Represents a music collection. Is actually an aggregate
 *  of <code>Song</code> objects, with the additional capacity of adding
 *  whole <code>Album</code> objects. <code>MusicCollection</code> objects hold no
 *  <code>Album</code> objects, but instead, the tracks composing the
 *  album are added one by one, after building a <code>Song</code> object from each of
 *  them.
 */

public class MusicCollection implements Serializable {
    public ArrayList< Song > items;

    public MusicCollection() {
        items = new ArrayList< Song >();
    }

    public void addSong( Song s ) {
        items.add( s );
    }

    public Song getSongAt( int index ) {
        return items.get(index);
    }

    public int getSize() {
        return items.size();
    }

    public void toListModel( DefaultListModel lm ) {
        lm.clear();
        for( Song s: items ) {
            lm.addElement( s );
        }
    }

    /** 
     *  All songs from an album are added to the collection
     *  @param album the album which will be added to the collection
     */
    public void addAlbum( Album a ) {
        for( String songTitle: a.getTrackList() ) {
            Song s = new Song( songTitle, a.artist, a.title );
            items.add( s );
        }
    }

    public String toString() {
        String retVal = "Music collection\n" + "----------------\n";

        for( Song s : items )
            retVal += ( s.toString() + '\n' );
        return retVal;
    }
}
