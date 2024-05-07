import java.sql.Timestamp;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents a file system containing directories and files.
 */

public class FileSystem {
    private Directory rootDirectory;
    private Directory currentDirectory;

    /**
     * Constructs a file system with a root directory.
     */
    public FileSystem() {
        // Creating the root directory
        rootDirectory = new Directory("", new Timestamp(System.currentTimeMillis()), null);
        currentDirectory = rootDirectory;
    }

     /**
     * Checks if the specified file system element exists.
     *
     * @param current the current file system element
     * @param target  the target file system element to find
     * @return true if the target exists, otherwise false
     */
    public boolean elementExists(FileSystemElement current, FileSystemElement target) {
        if (current == target) {
            return true;
        }
    
        if (current instanceof Directory) {
            Directory directory = (Directory) current;
            for (FileSystemElement child : directory.getChildren()) {
                if (elementExists(child, target)) {
                    return true;
                }
            }
        }
    
        return false;
    }

    /**
     * Gets the current directory of the file system.
     *
     * @return the current directory
     */
    public Directory getCurrentDirectory(){
        return currentDirectory;
    }

     /**
     * Gets the root directory of the file system.
     *
     * @return the root directory
     */
    public Directory getRootDirectory(){
        return rootDirectory;
    }
    
    /**
     * Checks if the specified directory exists in the file system.
     *
     * @param current the current file system element
     * @param target  the target directory to find
     * @return true if the directory exists, otherwise false
     */
    public boolean directoryExists(FileSystemElement current, Directory target) {
        if (current == target) {
            return true;
        }
    
        if (current instanceof Directory) {
            Directory directory = (Directory) current;
            for (FileSystemElement child : directory.getChildren()) {
                if (child instanceof Directory) {
                    if (directoryExists(child, target)) {
                        return true;
                    }
                }
            }
        }
    
        return false;
    }

    /**
     * Gets the path from the root directory to the target file system element.
     *
     * @param current     the current file system element
     * @param target      the target file system element
     * @param pathBuilder the string builder to build the path
     */
    public void getPathFromRoot(FileSystemElement current, FileSystemElement target, StringBuilder pathBuilder) {
        if (current == null || target == null) {
            return;
        }
    
        if (current == target) {
            pathBuilder.insert(0, current.getName());
            FileSystemElement parent = current.getParent();
            while (parent != null) {
                pathBuilder.insert(0, "/");
                pathBuilder.insert(0, parent.getName());
                parent = parent.getParent();
            }
            return;
        }
    
        if (current instanceof Directory) {
            Directory directory = (Directory) current;
            for (FileSystemElement child : directory.getChildren()) {
                getPathFromRoot(child, target, pathBuilder);
                if (pathBuilder.length() > 0) {
                    pathBuilder.insert(0, "/");
                    pathBuilder.insert(0, directory.getName());
                    break;
                }
            }
        }
    }

    /**
     * Checks if the current directory contains a child with the specified name.
     *
     * @param target the name of the child to find
     * @return true if the child exists, otherwise false
     */
    public boolean containsChild(String target) {
        for (FileSystemElement child : currentDirectory.getChildren()) {
            if (child.getName().equals(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a new file in the current directory with the specified name.
     *
     * @param name the name of the file to create
     */
    public void createFile(String name){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        File file = new File(name, timestamp, currentDirectory);
        currentDirectory.addChild(file);
        System.out.println("File created: " + name);
    }

    /**
     * Creates a new directory in the current directory with the specified name.
     *
     * @param name the name of the directory to create
     */
    public void createDirectory(String name){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Directory directory = new Directory(name, timestamp, currentDirectory);
        currentDirectory.addChild(directory);
        System.out.println("Directory created: " + name + "/");
    }

    /**
     * Deletes the file or directory with the specified name from the current directory.
     *
     * @param name the name of the file or directory to delete
     */
    public void delete(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Error: Invalid file or directory name.");
            return;
        }
    
        if (currentDirectory.getParent() == null) {
            System.out.println("Error: Root directory cannot be deleted!");
            return;
        }
    
        boolean deleted = false;
        for (FileSystemElement child : currentDirectory.getChildren()) {
            if (child.getName().equals(name)) {
                if (child instanceof Directory) {
                    Directory directory = (Directory) child;
                    for (FileSystemElement element : directory.getChildren()) {
                        delete(element.getName());
                    }
                    deleted = true;
                    System.out.println("Directory deleted: " + name);
                } else {
                    deleted = true;
                    System.out.println("File deleted: " + name);
                }
                currentDirectory.removeChild(child);
                break;
            }
        }
    
        if (!deleted) {
            System.out.println("Error: File or directory not found.");
        }
    }

    /**
     * Moves the source file system element to the specified destination path.
     *
     * @param sourceElement   the source file system element to move
     * @param destinationPath the destination path to move the element to
     */
    public void move(FileSystemElement sourceElement, String destinationPath) {
        // Split the destination path into directory names
        String[] destinationDirectories = destinationPath.split("/");
    
        // Start from the root directory for the destination
        FileSystemElement currentElement = rootDirectory;
    
        // Traverse each directory in the destination path
        for (String directory : destinationDirectories) {
            // Check if the current element is a directory
            if (currentElement instanceof Directory) {
                Directory currentDirectory = (Directory) currentElement;
                // Search for the next directory in the current directory's children
                boolean found = false;
                for (FileSystemElement child : currentDirectory.getChildren()) {
                    if (child.getName().equals(directory)) {
                        currentElement = child;
                        found = true;
                        break;
                    }
                }
                // If the directory is not found, print an error and return
                if (!found) {
                    System.out.println("Error: Destination directory not found.");
                    return;
                }
            } else {
                // If the current element is not a directory, print an error and return
                System.out.println("Error: Destination path is not valid.");
                return;
            }
        }
    
        // Check if the source element has a parent
        if (sourceElement.getParent() != null) {
            // Remove the source element from its current parent
            Directory parentDirectory = (Directory) sourceElement.getParent();
            parentDirectory.removeChild(sourceElement);
        } else {
            // If the source element has no parent, it is the root directory
            // Do not allow moving the root directory
            System.out.println("Error: Cannot move the root directory.");
            return;
        }
    
        // Add the source element to the destination directory
        Directory destinationDirectory = (Directory) currentElement;
        destinationDirectory.addChild(sourceElement);
    
        // Print the message
        System.out.println("File moved: " + sourceElement.getName() + " to " + destinationPath);
    }
    
    
     /**
     * Gets the current path of the specified file system element.
     *
     * @param element the file system element
     * @return the current path of the element
     */
    public String getCurrentPath(FileSystemElement element) {
        Stack<String> pathStack = new Stack<>();
        while (element != null) {
            pathStack.push(element.getName());
            element = element.getParent();
        }
        StringBuilder pathBuilder = new StringBuilder();
        while (!pathStack.isEmpty()) {
           
            pathBuilder.append(pathStack.pop());
            pathBuilder.append("/");
        }
        return pathBuilder.toString();
    }

    /**
     * Searches for a file or directory with the specified name in the current directory.
     *
     * @param name      the name of the file or directory to search for
     * @param directory the directory to search in
     * @return the path to the found file or directory, or an error message if not found
     */
    public String search(String name, Directory directory) {
        // Check if the directory contains the file
        for (FileSystemElement child : directory.getChildren()) {
            if (child.getName().equals(name)) {
                return getCurrentPath(child.getParent());
            }
        }
    
        // Recursively search in subdirectories
        for (FileSystemElement child : directory.getChildren()) {
            if (child instanceof Directory) {
                Directory subDirectory = (Directory) child;
                String result = search(name, subDirectory);
                if (!result.startsWith("Error")) {
                    return "Found: " + result + name;
                }
            }
        }
    
        // If the file is not found, return an error message
        return "Error: '" + name + "' not found.";
    }

    /**
     * Prints the directory tree starting from the specified file system element.
     *
     * @param element the starting file system element
     * @param depth   the initial depth of the directory tree
     */
    public void printDirectoryTree(FileSystemElement element, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  "); // Indentation for visual representation
        }
        String type1 = (element instanceof Directory) ? "* " : "  ";
        String type2 = (element instanceof Directory) ? "/" : ""; 
        System.out.println(type1 + element.getName() + type2);
        if (element instanceof Directory) {
            Directory directory = (Directory) element;
            for (FileSystemElement child : directory.getChildren()) {
                printDirectoryTree(child, depth + 1);
            }
        }
    }

    /**
     * Lists the contents of the current directory.
     */
    public void listContents() {
        for (FileSystemElement child : currentDirectory.getChildren()) {
            if (child instanceof Directory) {
                System.out.println("* " + child.getName() + "/");
            } else {
                System.out.println("* " + child.getName());
            }
        }
    }
    
     /**
     * Lists the contents of the specified directory sorted by date created.
     *
     * @param directory the directory to list contents from
     */
    public void listContentsByCreationDate(Directory directory) {
        if (directory == null) {
            System.out.println("Error: Directory is null.");
            return;
        }
        if (directory.getChildren().isEmpty()) {
            System.out.println("Directory is empty.");
            return;
        }
        List<FileSystemElement> elements = new ArrayList<>(directory.getChildren());
        Collections.sort(elements, new Comparator<FileSystemElement>() {
            @Override
            public int compare(FileSystemElement o1, FileSystemElement o2) {
                return o1.getDateCreated().compareTo(o2.getDateCreated());
            }
        });
        System.out.println("Sorted contents of " + getCurrentPath(currentDirectory) + " by date created:");
        for (FileSystemElement element : elements) {
            String type = (element instanceof Directory) ? "/" : "";
            System.out.println("* " + element.getName() + type + " (" + element.getDateCreated() + ")");
        }
    }

     /**
     * Changes the current directory to the specified new path.
     *
     * @param newPath the new path to change to
     */
    public void changeDirectory(String newPath) {
        // Remove leading and trailing whitespace
        newPath = newPath.trim();
        // Remove leading '/' if present
        if (newPath.startsWith("/")) {
            newPath = newPath.substring(1);
        }
        // Check if newPath is empty or equals to "root"
        if (newPath.isEmpty() || newPath.equalsIgnoreCase("root")) {
            currentDirectory = rootDirectory;
            System.out.println("Directory changed to: " + getCurrentPath(currentDirectory));
            return;
        }
        // Split the newPath into directory names
        String[] directories = newPath.split("/");
        // Start from the root directory
        Directory current = rootDirectory;
        // Traverse each directory in the newPath
        for (String directory : directories) {
            // Check if the directory exists in the current directory
            boolean found = false;
            for (FileSystemElement child : current.getChildren()) {
                if (child instanceof Directory && child.getName().equals(directory)) {
                    current = (Directory) child;
                    found = true;
                    break;
                }
            }
            // If directory not found, print error and return
            if (!found) {
                System.out.println("Error: Directory '" + directory + "' not found.");
                return;
            }
        }
        // Update the current directory
        currentDirectory = current;
        // Print the new current path
        System.out.println("Directory changed to: " + getCurrentPath(currentDirectory));
    }
    
    
}