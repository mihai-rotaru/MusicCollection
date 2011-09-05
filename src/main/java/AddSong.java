import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


public class AddSong extends JDialog {
    public Song song;
    Core application;
    public boolean active;

    public AddSong( Core app ) {
        active=true;
        song = null;
        application = app;

        JLabel lblArtist = new JLabel("Artist: ");
        JLabel lblTitle = new JLabel("Title: ");
        JLabel lblAlbum = new JLabel("Album: ");

        JTextField txtArtist = new JTextField();
        JTextField txtTitle = new JTextField();
        JTextField txtAlbum = new JTextField();

        JButton btnOK = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");
        
        setLayout( new GridLayout( 0,2 ));
        add( lblArtist );
        add( txtArtist );
        add( lblTitle );
        add( txtTitle );
        add( lblAlbum );
        add( txtAlbum );
        add( btnOK );
        add( btnCancel );
        
        final String title = txtTitle.getText().trim();
        final String artist = txtArtist.getText().trim();
        final String album = txtAlbum.getText().trim();

        btnOK.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                song = new Song( title, artist, album );
                close();
            }
        });

        setSize( new Dimension( 400, 150 ));
        setLocationRelativeTo(null);
        setVisible( true );
    }


    public void close() {
        application.retSong = song;
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
        active = false;
    }

//    public static void main( String[] args ) 
//    {
//        new AddSong();
//    }
}
