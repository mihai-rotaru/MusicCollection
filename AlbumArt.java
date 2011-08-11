import java.awt.*;
import java.awt.event.*;
import javax.swing. *;

class AlbumArt extends JFrame implements KeyListener {

    public AlbumArt(){
       this.setLayout( new GridBagLayout ());
       setSize( new Dimension( 740,480 ));
       setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );









        setVisible(true);
    }

    // implement KeyListener
    @Override public void keyPressed( KeyEvent e ){
    }

    @Override public void keyReleased( KeyEvent e ){
    }

    @Override public void keyTyped( KeyEvent e ){
    }


    public static void main( String[] args ){
        AlbumArt a = new AlbumArt();
    }

}
