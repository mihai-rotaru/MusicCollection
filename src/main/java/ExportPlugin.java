
/**
 *  Plugins implementing this interface need to export from
 *  MusicCollection to a particular file format.
 */
public interface ExportPlugin {

    /**
     *   Save the MusicCollection to disk in the specified file format
     *   @param fileName The file to which to export the MusicCollection
     *   @param mcoll The MusicCollection to be exported
     */
    public void exportMusicCollection( String fileName, MusicCollection mcoll );

    /**
     *   This function will return the extensions corresponding
     *   to the file format to which the plugin can export
     */
    public String extensions();

    /**  Returns information about the plugin, such as author, version,
     *   website and a short description.
     */
    public PluginMeta about();
}
