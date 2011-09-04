import java.io.*;

public class ExportToHTML implements ExportPlugin {

    public void exportMusicCollection( String fileName, MusicCollection mcoll ) {
        try {
            // Create file 
            FileWriter fstream = new FileWriter( fileName );
            BufferedWriter out = new BufferedWriter(fstream);

            // write entries
            for( int i=0; i < mcoll.getSize(); i++ ) {
                out.write( mcoll.getSongAt( i ).toString());
            }

                //Close the output stream
                out.close();
            } catch( Exception e ) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        public String extensions() {
            return "html";
        }

        public PluginMeta about() {
            return new PluginMeta( ExportToHTML.class.getName() );
        }
    }
