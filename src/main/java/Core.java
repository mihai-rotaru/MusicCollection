import java.util.ServiceLoader;
import java.util.Iterator;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


/**
 * The core of the program. This class uses the singleton design pattern
 * to ensure, that only one <code>Core</code> object can be instantiated.
 */
public class Core {

    /**
     * Reference to the running application
     */
    private static Core app;

    /**
     * The commandline arguments, as passed to the <code>main()</code> method.
     */
    private String[] args;

    /**
     *  Plugins are loaded and held in HashMaps
     */
    HashMap artworkPlugins = new HashMap();
    HashMap importPlugins = new HashMap();
    HashMap exportPlugins = new HashMap();


    /**
     * GUI variables
     */
    private JFrame f = new JFrame("Basic GUI"); //create Frame
    private JPanel pnlNorth = new JPanel(); // North quadrant 
    private JPanel pnlSouth = new JPanel(); // South quadrant
    private JPanel pnlEast = new JPanel(); // East quadrant
    private JPanel pnlWest = new JPanel(); // West quadrant
    private JPanel pnlCenter = new JPanel(); // Center quadrant


    // Buttons some there is something to put in the panels
    private JButton btnNorth = new JButton("North");
    private JButton btnSouth = new JButton("South");
    private JButton btnEast = new JButton("East");
    private JButton btnWest = new JButton("West");
    private JButton btnCenter = new JButton("Center");


    /**
     * Construct a new application core
     * @param args arguments, as passed from the main() method
     */
    private Core() {
        launchGUI();
    }

    /**
     * Create and display the GUI
     */
    private void launchGUI() {

        // Add Buttons
        pnlNorth.add(btnNorth);
        pnlSouth.add(btnSouth);
        pnlEast.add(btnEast);
        pnlWest.add(btnWest);
        pnlCenter.add(btnCenter);


        // Setup Main Frame
        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(pnlNorth, BorderLayout.NORTH);
        f.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
        f.getContentPane().add(pnlEast, BorderLayout.EAST);
        f.getContentPane().add(pnlWest, BorderLayout.WEST);
        f.getContentPane().add(pnlCenter, BorderLayout.CENTER);


        // Allows the Swing App to be closed
        f.addWindowListener(new ListenWindowClosed());


        // show 'em
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }


    /**
     * This function is called when the window is closed
     */
    public class ListenWindowClosed extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);         
        }
    }

    /**
     * Create a new application instance. This method can only be called once.
     * subsequent calls do nothing besides returning a reference to the running
     * program.
     * @param args commandline arguments, as passed to the <code>main()</code>
     * method.
     */
    public static void newInstance(String[] args) {
        if (app==null) {
            app = new Core();
            app.args=args;

            app.autoStart();

        }
    }

    /**
     * Find all registered service providers ( plugins ),
     * and load them in the corresponding HashMap
     */
    private void autoStart () {
        // load 'artwork' plugins
        ServiceLoader artwork_plugins = ServiceLoader.load(ArtworkGetterPlugin.class);
        Iterator it = artwork_plugins.iterator();
        while (it.hasNext()) {
            ArtworkGetterPlugin agp = (ArtworkGetterPlugin)it.next();
            String pluginName = agp.about().getName();
            artworkPlugins.put( pluginName, agp );
            System.out.println("loaded artworkPlugin: " + pluginName );
        }

        // load import plugins
        ServiceLoader import_plugins = ServiceLoader.load(ImportPlugin.class);
        it = import_plugins.iterator();
        while (it.hasNext()) {
            ImportPlugin ip = (ImportPlugin)it.next();
            String pluginName = ip.about().getName();
            importPlugins.put( pluginName, ip );
            System.out.println("loaded importPlugin: " + pluginName );
        }

        // load export plugins
        ServiceLoader export_plugins = ServiceLoader.load(ExportPlugin.class);
        it = export_plugins.iterator();
        while (it.hasNext()) {
            ExportPlugin ep = (ExportPlugin)it.next();
            String pluginName = ep.about().getName();
            exportPlugins.put( pluginName, ep );
            System.out.println("loaded exportPlugin: " + pluginName );
        }

        ArtworkGetterPlugin agp = (ArtworkGetterPlugin)artworkPlugins.get("GoogleImages");
        agp.initialize( getApp() );

        // GUI
        launchGUI();
    }

    /**
     * Query the running core
     * @return the <code>Core</code> object of the running application
     */
    public static Core getApp() {
        return app;
    }

    /**
     * Query the commandline arguments, this application was started with
     * @return commandline arguements, as passed to <code>main()</code>.
     */
    public String[] getArgs() {
        return args;
    }
}
