import java.io.*;

/** This plugin can import a music collection from an m3u
 *  file.
 */
public class ImportM3U implements ImportPlugin {

    public MusicCollection importFile( String fileName ) {
        try {
            FileInputStream fstream = new FileInputStream( fileName );
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
                System.out.println (strLine);
            }
            in.close();
        } catch( Exception e ) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    public String extensions() {
        return "m3u";
    }

    public PluginMeta about() {
        return new PluginMeta( ImportM3U.class.getName());
    }
}
