import java.sql.Timestamp;
import java.util.LinkedList;

/**
 * Represents a directory in the file system.
 * Inherits properties and functionality from the FileSystemElement abstract class.
 */
public class Directory extends FileSystemElement {
    private LinkedList<FileSystemElement> children;

    /**
     * Constructs a directory with the specified name, creation date, and parent directory.
     *
     * @param name        the name of the directory
     * @param dateCreated the timestamp representing the creation date of the directory
     * @param parent      the parent directory of the directory
     */
    public Directory(String name, Timestamp dateCreated, FileSystemElement parent) {
        super(name, dateCreated, parent);
        children = new LinkedList<>();
    }

    /**
     * Adds a child element to this directory.
     *
     * @param child the child element to be added
     */
    public void addChild(FileSystemElement child) {
        children.add(child);
    }

    /**
     * Removes a child element from this directory.
     *
     * @param child the child element to be removed
     */
    public void removeChild(FileSystemElement child) {
        children.remove(child);
    }

    /**
     * Returns the list of children elements of this directory.
     *
     * @return the list of children elements
     */
    public LinkedList<FileSystemElement> getChildren() {
        return children;
    }
}

