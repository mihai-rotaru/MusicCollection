import java.util.ServiceLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO; 
import java.awt.Graphics;
import java.awt.Graphics2D; 
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;





/**
 * The core of the program. This class uses the singleton design pattern
 * to ensure, that only one <code>Core</code> object can be instantiated.
 */
public class Core  extends JFrame implements KeyListener, ListDataListener {

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
     *  The currently loaded collection
     */
    MusicCollection ccoll;

    /**
     *  Used when a new Song or Album is added
     */
    public Song retSong;
    public Album retAlbum;

    /**
     *  List model to link/sync the JList with the current collection
     */
    ListModel songData = new AbstractListModel() {
        public int getSize() { 
            return ccoll.getSize(); 
        }
        
        public Object getElementAt(int index) { 
            if( ccoll.getSize() > 0 )
                return ccoll.getSongAt( index ); 
            else return null;
        }
    };



    /**
     * GUI variables
     */
    private JFrame f = new JFrame("Basic GUI");
    private JPanel pnlNorth = new JPanel();
    private JPanel pnlSouth = new JPanel();
    private JPanel pnlEast = new JPanel();
    private JPanel pnlWest = new JPanel();
    private JPanel pnlCenter = new JPanel();


    // controls
    private JLabel lblCurrentColl = new JLabel("Current collection: ");
    private JLabel lblCurrentCollName = new JLabel("no collection loaded");
    private JButton btnSave = new JButton("Save");
    private JButton btnSaveAs = new JButton("Save As...");
    private JButton btnLoad = new JButton("Load...");
    private JButton btnAddSong = new JButton("Add Song");
    private JButton btnAddAlbum = new JButton("Add Album");
    JButton btnDl = new JButton( "Download Artwork" );

    JLabel lblArtist = new JLabel("Artist:                        ");
    JLabel lblTitle = new JLabel("Title:                          ");
    JLabel lblAlbum = new JLabel("Album:                          ");

    JLabel lblArtist2 = new JLabel("");
    JLabel lblTitle2 = new JLabel("");
    JLabel lblAlbum2 = new JLabel("");

    DefaultListModel listModel = new DefaultListModel();


    private JList list = new JList( listModel );

    /**
     * Construct a new application core
     * @param args arguments, as passed from the main() method
     */
    private Core() {
    }

    /** 
     *  Implement the <code>KeyListener</code> interface
     */
    public void keyTyped( KeyEvent e ) {
    }

    public String getArtwork( Song s ) {
        String path = "cover_" + s.artist + "_" + s.album + ".jpg";
        File f = new File( path );

        if( !f.exists()){
            System.out.println("downloading artwork for " + s.toString());

            ArtworkGetterPlugin agp = (ArtworkGetterPlugin)artworkPlugins.get("GoogleImages");
            Image image = agp.getAlbumArtwork( s.artist, s.album );
            if( image == null ) {
                System.out.println("download failed.");
                return null;
            }

            BufferedImage cpimg = Utils.toBufferedImage( image );

            try {
                ImageIO.write(cpimg, "jpg", f); 
                return path;
            } catch( java.io.IOException e ) {
                e.printStackTrace();
            }
        }
        return path;
    }



    public void keyPressed( KeyEvent e ) {
        if( e.getKeyCode() == e.VK_DELETE ) {
            System.out.println("Delete pressed");
            if( list.getSelectedIndex() != -1 ) {
                listModel.remove( list.getSelectedIndex());
            }
        }
    }

    public void keyReleased( KeyEvent e ) {
    }

    /** 
     *  Implement the <code>ListDataListener</code> interface
     */
    public void contentsChanged(ListDataEvent e) {
        int i1 = e.getIndex0();
        int i2 = e.getIndex1();
        System.out.println("contentsChanged: " + i1 + ", " + i2 + '\n' );
//        if( i1 == i2 && i1 < ccoll.getSize()) // only one item was changed
//            ccoll.items.set( i1, (Song)listModel.get(i1));

        System.out.println( ccoll );
    }
    
    public void intervalAdded(ListDataEvent e) {
        System.out.println("intervalAdded: " + e.getIndex0() + ", " + e.getIndex1() + '\n' );
        System.out.println( ccoll );
    }

    public void intervalRemoved(ListDataEvent e) {
        int i1 = e.getIndex0();
        int i2 = e.getIndex1();
        System.out.println("intervalRemoved: " + e.getIndex0() + ", " + e.getIndex1() + '\n' );
//        System.out.println( listModel.getSize() );

//        if( i1 == i2 && i1 < ccoll.getSize())
//        {

//            System.out.println( "removein" );
//            
//            Song s = (Song)listModel.get(i1);

//            ccoll.items.remove( listModel.get( i1 ));
//        }
//        System.out.println( ccoll );
    }

    /**
     * Create and display the GUI
     */
    private void launchGUI() {

//        btnSave.setPreferredSize(new Dimension(100, 100));
        setResizable( false );
                    
        pnlNorth.setSize( 300, 90 );
        pnlNorth.addKeyListener( this );
        pnlNorth.setBackground( new Color( 224,224,224 ));
        pnlSouth.setBackground( new Color( 224,224,224 ));
//        pnlNorth.setLayout( new BoxLayout( pnlNorth, BoxLayout.LINE_AXIS));
        pnlNorth.setLayout( new FlowLayout( FlowLayout.LEADING ));
        pnlSouth.setLayout( new FlowLayout( FlowLayout.LEADING ));

        list.setPreferredSize( new Dimension( 300, 400 ));


        // Add Buttons
        pnlNorth.add( lblCurrentColl );
        pnlNorth.add( lblCurrentCollName );
        pnlNorth.add( btnSave );
        pnlNorth.add( btnSaveAs );
        pnlNorth.add( btnLoad );
        pnlWest.add( list );
//        pnlSouth.add( btnAddSong );
//        pnlSouth.add( btnAddAlbum );
        pnlSouth.add( btnDl );

//        pnlEast.setLayout( new FlowLayout( FlowLayout.LEADING));
//        pnlEast.add( lblArtist );
//        pnlEast.add( lblArtist2 );
//        pnlEast.add( lblTitle );
//        pnlEast.add( lblTitle2 );
//        pnlEast.add( lblAlbum );
//        pnlEast.add( lblAlbum2 );

        // Setup Main Frame
        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(pnlNorth, BorderLayout.NORTH);
        f.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
        f.getContentPane().add(pnlEast, BorderLayout.EAST);
        f.getContentPane().add(pnlWest, BorderLayout.WEST);
        f.getContentPane().add(pnlCenter, BorderLayout.CENTER);

        // add some songs
        ccoll = new MusicCollection();
//        Album a = new Album( "Rammstein", "Rosenrot" );
//        a.addTrack( "Benzin" );
//        a.addTrack( "Mann gegen Mann" );
//        a.addTrack( "Rosenrot" );
//        ccoll.addAlbum( a );
        Song s1 = new Song("Palovana","Korpiklaani","Tervaskanto");
        Song s2 = new Song("Du Hast","Rammstein","Sehnsucht");
        Song s3 = new Song("Binaz In Dub","Shantel","Sehnsucht");
        ccoll.addSong( s1 );
        ccoll.addSong( s2 );
        ccoll.addSong( s3 );
//        list.setListData( ccoll.items.toArray() );
        
        // Allows the Swing App to be closed
        f.addWindowListener(new ListenWindowClosed());

        // process keyboard imput
        f.addKeyListener( this );
        list.addKeyListener( this );
        listModel.addListDataListener( this );
        listModel.addElement( new Song("JJJJ", "Some track" ));
        ccoll.toListModel( listModel );

        // DOWNlad
        btnLoad.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
            }
        });

        // LOAD
        btnLoad.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                JFileChooser c = new JFileChooser(".");

                // default file filter: *.mcoll files
                c.setFileFilter( new javax.swing.filechooser.FileFilter() {
                    @Override public boolean accept(File f) {
                        return ( f.getName().endsWith(".mcoll") && f.isFile()); 
                    }

                    @Override public String getDescription() {
                        return "Only .mcoll files";
                    }
                });

                int rVal = c.showOpenDialog( getApp() );
                if (rVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("Loading: " + c.getSelectedFile().getAbsolutePath());



                    btnSave.setEnabled( false );
                }
                else if (rVal == JFileChooser.CANCEL_OPTION) {
                    System.out.println("cancelled");
                }
            }
        });

        // ADD SONG
        btnAddSong.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                retSong = null;
//                AddSong as = new AddSong( Core.getApp());
//                
//                System.out.println( "SONG:" );
//                System.out.println( retSong );
//                listModel.addElement( retSong );
//                AboutDialog dlg = new AboutDialog(new JFrame(), "title", "message");


                ///****!!!!!!!!!!!!!!!!
//                String artist = showInputDialog( Core.getApp(), null, "Please enter the artist's name", JOptionPane.QUESTION_MESSAGE);
            }
        });

        // show 'em
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        setLocationRelativeTo(null);
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
        ccoll = null;

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
