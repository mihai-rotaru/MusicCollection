/**
 *  Plugins implementing this interface need to import from
 *  a particular file format.
 */
public interface ImportPlugin {

    /**
     *   Parse a MusicCollection from a file
     *   @param fileName The file from which to import the MusicCollection
     */
    public MusicCollection importFile( String fileName );

    /**
     *   This function will return the extensions corresponding
     *   to the file format from which the plugin can import
     */
    public String extensions();

    /**  Returns information about the plugin, such as author, version,
     *   website and a short description.
     */
    public PluginMeta about();
}
