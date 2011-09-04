public class ExportToTXT implements ExportPlugin {
    
    public void exportMusicCollection( String fileName, MusicCollection mcoll ) {
    }

    public String extensions() {
        return "txt";
    }

    public PluginMeta about() {
        return new PluginMeta( ExportToTXT.class.getName() );
    }
}
