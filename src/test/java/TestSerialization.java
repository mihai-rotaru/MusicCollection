import java.io.*;

import junit.framework.TestCase;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

/** 
 *   Test the correct functioning of the serialization of
 *   <code>MusicCollection</code> objects.
 */
public class TestSerialization {

    MusicCollection mc;
    Song s;
    ObjectOutputStream out;
    Album a;

    String folderName = "build/junit/data";
    String fileName = "build/junit/data/mcoll1";

    @org.junit.Before
    public void SetUp() {
        mc = new MusicCollection();
        s = new Song("Du Hast", "Rammstein" );
        a = new Album( "Rammstein", "Rosenrot" );

        assertNotNull( "Failed to create MusicCollection object", mc );
        assertNotNull( "Failed to create Song object", s );
        assertNotNull( "Failed to create an Album object", a );
    }

    @org.junit.Test
    public void TestAddSong() {
        mc.addSong( s );
        assertNotNull( mc.getSongAt(0) );
    }

    /** Create a MusicCollection object, and add an album with
     *  three tracks to it.
     */
    void initMCWithAlbum() {
        mc = new MusicCollection();
        a = new Album( "Rammstein", "Rosenrot" );
        a.addTrack( "Benzin" );
        a.addTrack( "Mann gegen Mann" );
        a.addTrack( "Rosenrot" );
        mc.addAlbum( a );
    }

    @org.junit.Test
    public void TestAddAlbum() {
        initMCWithAlbum();
        assertTrue( mc.getSize() == 3 );
    }

    @org.junit.Test
    public void TestSerialize() {
        initMCWithAlbum();
        try {
            File dir = new File( folderName );

            if( dir.exists() == false ) {
                dir.mkdir();
            }
            else{
                System.out.println("Directory is not created");
            }

            out = new ObjectOutputStream( new FileOutputStream( fileName ));

            assertNotNull( out );

            out.writeObject( mc );
            out.close();
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void TestDeSerialize() {
        try {
            ObjectInputStream in = new ObjectInputStream( new FileInputStream( fileName ));
            assertNotNull( "Failed to create ObjectInputStream for " + fileName, in );

            MusicCollection mc2 = (MusicCollection) in.readObject();
            assertNotNull( "Failed to create MusicCollection object", mc2 );

            assertTrue( "DeSerialized MusicCollection should have three elements",
                    mc2.getSize() == 3 );
            assertTrue( "Wrong deserialized result", 
                    mc2.getSongAt(0).toString().equals( "Rammstein - Benzin" ));
            in.close();
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
