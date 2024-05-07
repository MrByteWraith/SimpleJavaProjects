import java.sql.Timestamp;

/**
 * Abstract class representing a file system element.
 * Provides basic properties and functionality common to both files and directories.
 */
public abstract class FileSystemElement {
    private String name;
    private Timestamp dateCreated;
    private FileSystemElement parent;

    /**
     * Constructs a file system element with the specified name, creation date, and parent directory.
     *
     * @param name        the name of the file system element
     * @param dateCreated the timestamp representing the creation date of the file system element
     * @param parent      the parent directory of the file system element
     */
    public FileSystemElement(String name, Timestamp dateCreated, FileSystemElement parent) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.parent = parent;
    }

    // Getters and setters

    /**
     * Returns the name of the file system element.
     *
     * @return the name of the file system element
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the file system element.
     *
     * @param name the new name of the file system element
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the creation date of the file system element.
     *
     * @return the creation date of the file system element
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the creation date of the file system element.
     *
     * @param dateCreated the new creation date of the file system element
     */
    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Returns the parent directory of the file system element.
     *
     * @return the parent directory of the file system element
     */
    public FileSystemElement getParent() {
        return parent;
    }

    /**
     * Sets the parent directory of the file system element.
     *
     * @param parent the new parent directory of the file system element
     */
    public void setParent(FileSystemElement parent) {
        this.parent = parent;
    }
}
