public class ExportToM3U implements ExportPlugin {
    
    public void exportMusicCollection( String fileName, MusicCollection mcoll ) {
    }

    public String extensions() {
        return "m3u";
    }

    public PluginMeta about() {
        return new PluginMeta( ExportToM3U.class.getName() );
    }
}
