public class ExportToHTML implements ExportPlugin {
    
    public void exportMusicCollection( String fileName, MusicCollection mcoll ) {
    }

    public String extensions() {
        return "html";
    }

    public PluginMeta about() {
        return new PluginMeta( ExportToHTML.class.getName() );
    }
}
