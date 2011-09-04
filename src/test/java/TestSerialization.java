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

    @org.junit.Test
    public void TestAddAlbum() {
        a.addTrack( "Benzin" );
        a.addTrack( "Mann gegen Mann" );
        a.addTrack( "Rosenrot" );
        
        mc.addAlbum( a );
        assertTrue( mc.getSize() == 3 );
    }

    @org.junit.Test
    public void TestSerialize() {
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
        assumeNotNull( out );

        try {
            ObjectInputStream in = new ObjectInputStream( new FileInputStream( fileName ));
            MusicCollection mc2 = (MusicCollection) in.readObject();
            assertTrue( "DeSerialized MusicCollection should have one element",
                    mc2.getSize() == 1 );
            assertTrue( "Wrong deserialized result", 
                    mc2.getSongAt(0).toString() == "Rammstein - Du Hast" );
            in.close();
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
