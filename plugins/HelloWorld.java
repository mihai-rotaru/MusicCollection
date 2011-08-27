import java.util.*;
import java.awt.Image; 

/**
 * A concrete plugin, that will not do much more, then simply print
 * a localized "Hello world" to the console.
 */
public class HelloWorld implements AutostartPlugin {

    public HelloWorld() {}

    public void initialize(Core core) {
        String tmp = "Something went wrong!";
        try {
            tmp= ResourceBundle.getBundle("i18n").getString("message");
        }
        catch (Exception e) {}

        System.out.println(tmp);
    }

    @Override public void getAlbumArtwork( String query, Image image ) {
    }

    @Override public void getAlbumMeta( String query, AlbumMeta albumMeta ) {
    }

    @Override public void about () {
    }
}
