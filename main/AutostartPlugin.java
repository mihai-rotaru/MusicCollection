/**
 * A class, that implements this interface and is a registered service 
 * provider will automatically be instantiated and run on program launch.
 */
public interface AutostartPlugin {

  /**
   * Called after instantiation to do initialization.
   * @param core Reference to the running application.
   */
  public void initialize (Core c);
}