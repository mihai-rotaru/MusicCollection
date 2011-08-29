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

    @Override public Image getAlbumArtwork( String query ) {
        return null;
    }

    @Override public AlbumMeta getAlbumMeta( String query ) {
        return null;
    }

    @Override public PluginMeta about () {
        return null;
    }
}
