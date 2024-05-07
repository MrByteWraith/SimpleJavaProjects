import java.util.Scanner;

/**
 * Main class provides a user interface for managing a file system.
 * Users can navigate directories, list directory contents, create/delete files and directories,
 * move files/directories, search for files/directories, print directory tree, and sort contents by date created.
 */
public class Main {
    
    /** The file system instance. */
    private static FileSystem fileSystem = new FileSystem();

    private static void displayMenu(){
        System.out.println("===== File System Management Menu =====");
        System.out.println("1. Change directory");
        System.out.println("2. List directory contents");
        System.out.println("3. Create file/directory");
        System.out.println("4. Delete file/directory");
        System.out.println("5. Move file/directory");
        System.out.println("6. Search file/directory");
        System.out.println("7. Print directory tree");
        System.out.println("8. Sort contents by date created");
        System.out.println("9. Exit");
        System.out.println("Please selecet an option: ");
}
private static void handleSelection(int selection, Scanner scanner) {
    switch (selection) {
        case 1:
            changeDirectoryOption(scanner);
            break;
        case 2:
            listDirectoryContents();
            break;
        case 3:
            createFileOrDirectoryOption(scanner);
            break;
        case 4:
            deleteFileOrDirectoryOption(scanner);
            break;
        case 5:
            moveFileOrDirectoryOption(scanner);
            break;
        case 6:
            searchFileOrDirectoryOption(scanner);
            break;
        case 7:
            printDirectoryTree();
            break;
        case 8:
            sortContentsByDateCreated();
            break;
        case 9:
            System.out.println("Exiting...");
            break;
        default:
            System.out.println("Invalid option. Please try again.");
            break;
    }
}

/**
 * Handles the "Change directory" option from the menu.
 * @param scanner Scanner object for user input.
 */
private static void changeDirectoryOption(Scanner scanner) {
    System.out.println("Current directory: " + fileSystem.getCurrentPath(fileSystem.getCurrentDirectory()));
    System.out.println("Enter new directory path: ");
    String newPath = scanner.nextLine();
    fileSystem.changeDirectory(newPath);
}

 /**
 * Handles the "List directory contents" option from the menu.
 */
private static void listDirectoryContents() {
    System.out.println("Current directory: " + fileSystem.getCurrentPath(fileSystem.getCurrentDirectory()));
    fileSystem.listContents();
}

 /**
 * Handles the "Create file/directory" option from the menu.
 * @param scanner Scanner object for user input.
 */
private static void createFileOrDirectoryOption(Scanner scanner) {
    System.out.println("Current directory: " + fileSystem.getCurrentPath(fileSystem.getCurrentDirectory()));
    System.out.println("Create file or directory (f/d): ");
    String decision = scanner.nextLine();
    if (!decision.equals("d") && !decision.equals("f")) {
        System.out.println("Wrong input!");
    } else if (decision.equals("d")) {
        System.out.println("Enter name for new directory: ");
        String newName = scanner.nextLine();
        if (fileSystem.containsChild(newName)) {
            System.out.println("Error: Associated name has been used already.");
        } else {
            fileSystem.createDirectory(newName);
        }
    } else {
        System.out.println("Enter name for new file: ");
        String newName = scanner.nextLine();
        if (fileSystem.containsChild(newName)) {
            System.out.println("Error: Associated name has been used already.");
        } else {
            fileSystem.createFile(newName);
        }
    }
}

/**
 * Handles the "Delete file/directory" option from the menu.
 * @param scanner Scanner object for user input.
 */
private static void deleteFileOrDirectoryOption(Scanner scanner) {
    System.out.println("Current directory: " + fileSystem.getCurrentPath(fileSystem.getCurrentDirectory()));
    System.out.println("Enter name of file/directory to delete: ");
    String name = scanner.nextLine();
    fileSystem.delete(name);
}

/**
 * Handles the "Move file/directory" option from the menu.
 * @param scanner Scanner object for user input.
 */
private static void moveFileOrDirectoryOption(Scanner scanner) {
    System.out.println("Current directory: " + fileSystem.getCurrentPath(fileSystem.getCurrentDirectory()));
    System.out.println("Enter name of file/directory to move: ");
    String target = scanner.nextLine();
    System.out.println("Enter new directory path: ");
    String newLoc = scanner.nextLine();
    FileSystemElement targetElement = null;
    for (FileSystemElement child : fileSystem.getCurrentDirectory().getChildren()) {
        if (child.getName().equals(target)) {
            targetElement = child;
            break;
        }
    }
    if (targetElement == null) {
        System.out.println("Error: Target file/directory not found in the current directory.");
        return;
    }
    fileSystem.move(targetElement, newLoc);
}

 /**
 * Handles the "Search file/directory" option from the menu.
 * @param scanner Scanner object for user input.
 */
private static void searchFileOrDirectoryOption(Scanner scanner) {
    System.out.println("Search query: ");
    String query = scanner.nextLine();
    System.out.println("Searching from root...");
    String res = fileSystem.search(query, fileSystem.getRootDirectory());
    System.out.println(res);
}


/**
 * Handles the "Print directory tree" option from the menu.
 */
private static void printDirectoryTree() {
    System.out.println("Path to current directory from root:");
    fileSystem.printDirectoryTree(fileSystem.getRootDirectory(), 0);
}

/**
 * Handles the "Sort contents by date created" option from the menu.
 */
private static void sortContentsByDateCreated() {
    fileSystem.listContentsByCreationDate(fileSystem.getCurrentDirectory());
}
    
    /**
     * Main method to start the file system management program.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int selection;
        do {
            displayMenu();
            selection = scanner.nextInt();
            scanner.nextLine();
            handleSelection(selection, scanner);
            } while (selection != 9);
        scanner.close();
    }
   
}
