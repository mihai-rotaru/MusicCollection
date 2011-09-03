import java.util.ServiceLoader;
import java.util.Iterator;


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
   * Construct a new application core
   * @param args arguments, as passed from the main() method
   */
  private Core() {
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

      MusicCollection mc = new MusicCollection();
      Song s = new Song("Du Hast", "Rammstein" );
      mc.addSong( s );
      System.out.println( s.toString() );

      app.autoStart();
    }
  }
  
  /**
   * Find all registered service providers for the <code>AutoStartPlugin</code>
   * interface, instantiate them and call their 
   * <code>initialize()</code> method.
   */
  private void autoStart () {
    ServiceLoader sl = ServiceLoader.load(AutostartPlugin.class);
    Iterator it = sl.iterator();
    while (it.hasNext()) {
      AutostartPlugin asp= (AutostartPlugin)it.next();
      asp.initialize(app);
      
    }
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
