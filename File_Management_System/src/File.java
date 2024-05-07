import java.sql.Timestamp;

/**
 * Represents a file in the file system.
 * Inherits properties and functionality from the FileSystemElement abstract class.
 */
public class File extends FileSystemElement {

    /**
     * Constructs a file with the specified name, creation date, and parent directory.
     *
     * @param name        the name of the file
     * @param dateCreated the timestamp representing the creation date of the file
     * @param parent      the parent directory of the file
     */
    public File(String name, Timestamp dateCreated, FileSystemElement parent) {
        super(name, dateCreated, parent);
    }
}
