import java.util.*;
import java.awt.Image; 
import java.net.URL; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO; 
import java.awt.Graphics;
import java.awt.Graphics2D; 
import java.io.File; 

/**
 * A concrete plugin, that will not do much more, then simply print
 * a localized "Hello world" to the console.
 */
public class HelloWorld implements AutostartPlugin {

    public HelloWorld() {}

    public void initialize(Core core) {
        System.out.println("This is the HelloWorld plugin!");

        Image image = getAlbumArtwork( "foo" );

        BufferedImage cpimg = Utils.toBufferedImage( image );
        File f1 = new File("test.png");

        try {
            ImageIO.write(cpimg, "png", f1); 
        } catch( java.io.IOException e ) {
        }
    }

    @Override public Image getAlbumArtwork( String query ) {
        URL url;
        Image image;
        try {
            url = new URL("http://cover-paradise.to/res/exe/GetElement.php?ID=770221");
            try {
                image = ImageIO.read(url); 
                return image;
            } catch( java.io.IOException e ) {
                return null;
            }
        } catch( java.net.MalformedURLException e ) {
        }
        return null;
    }

    @Override public AlbumMeta getAlbumMeta( String query ) {
        return null;
    }

    @Override public PluginMeta about () {
        return null;
    }
}
