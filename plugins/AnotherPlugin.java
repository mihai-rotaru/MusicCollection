import java.util. *;
import java.awt.Image; 

/**
 * Another plugin, let's see if it will be loaded
 */
public class AnotherPlugin implements AutostartPlugin {

    public AnotherPlugin () {}

    public void initialize( Core core ) {
        System.out.println("Hello, this is another plugin!");
    }

    @Override public void getAlbumArtwork( String query, Image image ) {
    }

    @Override public void getAlbumMeta( String query, AlbumMeta albumMeta ) {
    }

    @Override public void about () {
    }
}
