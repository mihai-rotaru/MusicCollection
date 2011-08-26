import java.util.*;

/**
 * A concrete plugin, that will not do much more, then simply print
 * a localized "Hello world" to the console.
 */
public class HelloWorld implements AutostartPlugin {
  
  public HelloWorld() {}
  
  public void initialize(Core core) {
    String tmp = "Something went wrong!";
    try {
      tmp= ResourceBundle.getBundle("i18n").getString("message");
    }
    catch (Exception e) {}
    
    System.out.println(tmp);
  }
}