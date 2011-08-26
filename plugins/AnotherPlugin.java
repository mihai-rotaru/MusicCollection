import java.util. *;

/**
 * Another plugin, let's see if it will be loaded
 */
public class AnotherPlugin implements AutostartPlugin {

    public AnotherPlugin () {}

    public void initialize( Core core ) {
        System.out.println("Fuck you, my friend!");
    }
}
