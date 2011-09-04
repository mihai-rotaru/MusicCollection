import java.io.*;

public class ExportToTXT implements ExportPlugin {
    
    public void exportMusicCollection( String fileName, MusicCollection mcoll ) {
        String newline = System.getProperty("line.separator");
        try {
            // Create file 
            FileWriter fstream = new FileWriter( fileName );
            BufferedWriter out = new BufferedWriter(fstream);

            // write entries
            for( int i=0; i < mcoll.getSize(); i++ ) {
                out.write( mcoll.getSongAt( i ).toString() + newline );
            }

            //Close the output stream
            out.close();
        } catch( Exception e ) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public String extensions() {
        return "txt";
    }

    public PluginMeta about() {
        return new PluginMeta( ExportToTXT.class.getName() );
    }
}
