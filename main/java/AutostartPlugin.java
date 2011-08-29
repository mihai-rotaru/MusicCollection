 import java.awt.Image; 

/**
 * A class, that implements this interface and is a registered service 
 * provider will automatically be instantiated and run on program launch.
 */
public interface AutostartPlugin {

  /**
   * Called after instantiation to do initialization.
   * @param core Reference to the running application.
   */
  public void initialize (Core c);

  /**
   *  Try to download the artwork for <code>query</code>
   *  It is assumed <code>query</code> is in the artist - album format
   *  @param query The string which to use when query-ing a site
   */
  public Image getAlbumArtwork( String query );

  /**
   * Try to get meta information about the album specified in the
   * <code>query</code>
   */
  public AlbumMeta getAlbumMeta( String query );

  /**
   *  Returns information about the plugin, such as author, version,
   *  website and a short description.
   */
  public PluginMeta about();
  
}
