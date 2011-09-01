import java.io.*;
import java.awt.Image; 
import java.awt.image.BufferedImage;
import javax.imageio.*;

import junit.framework.TestCase;
import static org.junit.Assert.*;

/** This file contains tests for the various utility functions 
 *  inside the Utils.java file.
 */
public class TestUtils {
    Image image;
    Image alphaImage;
    BufferedImage buffImage;
    InputStream input;
    Utils cUtils;

    @org.junit.Before
    public void SetUp() {
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        input = classLoader.getResourceAsStream("flower.png");
        assertNotNull( "Failed to getResourceAsStream ", input );

        try {
        image = ImageIO.read(input);
        } catch( IOException e ) {
            e.printStackTrace();
            fail("Could not read input as an image");
        }

        cUtils = new Utils();
    }

    @org.junit.Test 
    public void testToBufferedImage() {
        buffImage = cUtils.toBufferedImage( image );
        assertTrue( "Conversion of Image to BufferedImage failed.", buffImage instanceof BufferedImage );
    }

    @org.junit.Test
    public void testHasAlpha() {
        assertTrue( "Failed to detect transparency.", cUtils.hasAlpha( image ) );
    }

}
