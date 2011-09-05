 import java.awt.Image; 

/**
 * A class, that implements this interface and is a registered service 
 * provider will automatically be instantiated and run on program launch.
 */
public interface ArtworkGetterPlugin {

  /**
   *  Called after instantiation to do initialization.
   *  @param core Reference to the running application.
   */
  public void initialize (Core c);

  /**
   *  Try to find and download the album cover for the
   *  album named <code>name</code>, by <code>artist</code>
   */
  public Image getAlbumArtwork( String artist, String title );

  /**
   *  Returns information about the plugin, such as author, version,
   *  website and a short description.
   */
  public PluginMeta about();
  
}
