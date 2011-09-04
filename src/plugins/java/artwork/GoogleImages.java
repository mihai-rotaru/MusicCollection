import java.util.*;
import java.awt.Image; 
import java.net.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO; 
import java.awt.Graphics;
import java.awt.Graphics2D; 
import java.io.*;

/**
 *  This plugin will look for the requested album cover on
 *  Google Images.
 */
public class GoogleImages implements ArtworkGetterPlugin {

    public void initialize(Core core) {
        System.out.println("This is the CoverDude plugin!");

        Image image = getAlbumArtwork( "Rammstein - Rosenrot" );
        if( image == null )
            return;

        BufferedImage cpimg = Utils.toBufferedImage( image );
        File f1 = new File("cover.jpg");

        try {
            ImageIO.write(cpimg, "jpg", f1); 
        } catch( java.io.IOException e ) {
        }
    }

    @Override public Image getAlbumArtwork( String query ) {
        URL url;
        Image image;
        InputStream is = null;
        DataInputStream dis;
        String s;
        String tempDirName = "temp";
        String fileName = "out.html";
        String link="http://www.google.com/images?hl=en-EN&q=Rammstein+mutter&sout=1";
            

        // get the HTTP file with the search results
        try {
            url = new URL( link );
            URLConnection uc;
            uc = url.openConnection();

            // fake a browser - otherwise, 403
            uc.addRequestProperty("User-Agent", 
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            StringBuilder parsedContentFromUrl = new StringBuilder();

            uc.connect();
            uc.getInputStream();
            BufferedInputStream in = new BufferedInputStream(uc.getInputStream());
            int ch;

            // build a string with the HTML file contents
            while ((ch = in.read()) != -1) {
                parsedContentFromUrl.append((char) ch);
            }
            
            try {

                // create the temp folder for intermediate html files
                File tempDir = new File( tempDirName );
                if( tempDir.exists() == false ) {
                    tempDir.mkdir();
                }
                else{
                    System.out.println("Directory is not created");
                }

                // create the temp file
                File tempFile = new File( tempDirName + "/" + fileName );
                if(!tempFile.exists())
                    tempFile.createNewFile();
                FileWriter fstream = new FileWriter( tempFile );
                BufferedWriter out = new BufferedWriter( fstream );

                // write the contents of the retrieved HTML file to disk
                out.write( parsedContentFromUrl.toString());
                out.close();

                // now, find the URL of the first image
                String HTMLdata = parsedContentFromUrl.toString();
                int anchor1 = HTMLdata.indexOf("imgres?imgurl");
                int anchor2 = HTMLdata.indexOf("&amp;imgrefurl=");
                String img1URLstr = HTMLdata.substring( anchor1 + 14, anchor2 );
                System.out.println ("url: " + img1URLstr);

                // return the image
                URL img1URL = new URL( img1URLstr);
                try {
                    image = ImageIO.read( img1URL );
                    return image;
                } catch( IOException e ) {
                    e.printStackTrace();
                }

                return null;
            } catch( java.io.IOException e ) {
                e.printStackTrace();
            }
        } catch( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override public PluginMeta about () {
        return new PluginMeta( GoogleImages.class.getName());
    }
}
