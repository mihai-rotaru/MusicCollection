import java.io.*;

/** This plugin can import a music collection from an m3u
 *  file.
 */
public class ImportM3U implements ImportPlugin {

    public MusicCollection importFile( String fileName ) {
        MusicCollection mc = new MusicCollection();
        Character sep = '\\';

        try {
            FileInputStream fstream = new FileInputStream( fileName );
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;

            //Read File Line By Line
            while (( line = br.readLine() ) != null )   {
                if( line.startsWith("#"))
                    continue;

                // parse artist and title of the song
                int p = line.lastIndexOf( sep );
                String fileNameE = line.substring( line.lastIndexOf( sep ) + 1 ).trim();
                String fileNameNoExt = fileNameE.substring( 0, fileNameE.lastIndexOf('.'));
                String artist = fileNameNoExt.substring( 0, fileNameNoExt.indexOf("-") - 1 ).trim(); 
                String title = fileNameNoExt.substring( fileNameNoExt.indexOf("-") + 1 ).trim(); 

                Song s = new Song( title, artist );
                mc.addSong( s );
            }
            in.close();
        } catch( Exception e ) {
            System.err.println("Error: " + e.getMessage());
        }
        return mc;
    }

    public String extensions() {
        return "m3u";
    }

    public PluginMeta about() {
        return new PluginMeta( ImportM3U.class.getName());
    }
}
