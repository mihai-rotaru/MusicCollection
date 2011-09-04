/** A structure to hold various details about a plugin. Each plugin
 *  has an 'about()' method, which will return an object of this type.
 */
public class PluginMeta {
    String name;
    String author;
    String description;
    String version;
    String website;

    public PluginMeta() {
        name = "plugin";
        author = "Annonymous";
        version = "1.0";
        website = "";
        description = "";
    }

    public PluginMeta( String _name ) {
        this();
        name = _name;
    }

    public PluginMeta( String _name, String _author ) {
        this();
        name = _name;
        author = _author;
    }

    public PluginMeta( String _name, String _author, String _version ) {
        this();
        name = _name;
        author = _author;
        version = _version;
    }

    public PluginMeta( String _name, String _author, String _version, String _website ) {
        this();
        name = _name;
        author = _author;
        version = _version;
        website = _website;
    }

    public String getName() {
        return name;
    }
}
