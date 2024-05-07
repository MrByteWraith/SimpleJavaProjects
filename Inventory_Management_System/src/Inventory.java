import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Inventory class represents a collection of electronic devices.
 * It provides methods to add, remove, and manipulate devices in the inventory.
 */
public class Inventory {
    /** List to store devices */
    public LinkedList<ArrayList<Device>> devices;
    
    /** Scanner object for user input */
    private Scanner scanner;

    /**
     * Constructs a new Inventory object.
     * Initializes the list of devices and the scanner.
     */
    public Inventory() {
        devices = new LinkedList<>();
        scanner = new Scanner(System.in);
    }

    /**
     * Adds a new device to the inventory.
     * 
     * @param deviceList The list containing the device to be added.
     */
    public void addDevice(ArrayList<Device> deviceList) {
        devices.add(deviceList);
    }

  /**
 * Removes a device from the inventory.
 * 
 * This method removes the specified device from the inventory. It searches
 * for the device object in the inventory and removes it if found.
 * 
 * @param device The device to be removed.
 * @complexity Time complexity: O(n * m), where n is the number of device lists
 *             and m is the average number of devices in each list.
 */
public void removeDevice(Device device) {
    for (ArrayList<Device> deviceList : devices) { // O(n) complexity, where n is the number of device lists
        for (Device d : deviceList) { // O(m) complexity, where m is the number of devices in each list
            if (d.equals(device)) {
                deviceList.remove(device); // O(m) complexity for removing from an ArrayList
                return;
            }
        }
    }
}


   /**
 * Updates the details of a device in the inventory.
 * 
 * This method updates the details of a device in the inventory. It searches for the
 * oldDevice object in the inventory and replaces it with the newDevice object.
 * If the oldDevice object is not found, the inventory remains unchanged.
 * 
 * @complexity: O(n * m), where n is the number of device lists in the inventory
 * and m is the number of attributes in each device.
 * 
 * @param oldDevice The old Device object whose details need to be updated.
 * @param newDevice The new Device object with updated details.
 */
public void updateDevice(Device oldDevice, Device newDevice) {
    for (ArrayList<Device> deviceList : devices) { // O(n) complexity, where n is the number of device lists
        for (Device d : deviceList) { // O(m) complexity, where m is the number of attributes in each device
            if (d.equals(oldDevice)) {
                deviceList.set(deviceList.indexOf(d), newDevice);
                return;
            }
        }
    }
}


    /**
     * Displays the list of all devices in the inventory.
     * 
     * This method prints out the details of all devices in the inventory, including category, name, price, and quantity.
     * 
     * @param m Number of device categories.
     * @param n Total number of devices.
     * @complexity Time Complexity: O(m * n), where m is the number of device categories and n is the total number of devices.
     */
    public void displayDevices() {
        int index = 1;
        System.out.println("Device List:");
        for (ArrayList<Device> deviceList : devices) { // O(m)
            for (Device device : deviceList) { // O(n)
                System.out.println(index + "." + "Category: " + device.getCategory() + ", Name:  " + device.getName() + ", Price: " + device.getPrice() + "$, Quantity: " + device.getQuantity());
                index++;
            }
        }
    }

    /**
     * Finds the cheapest product in the inventory.
     * 
     * This method iterates through each device category and finds the device with the lowest price.
     * 
     * @param m The number of device categories.
     * @param n The total number of devices.
     * @return The cheapest device found in the inventory.
     * @complexity Time Complexity: O(m * n), where m is the number of device categories and n is the total number of devices.
     */
    public Device findDeviceWithMinimumPrice() {
        if (devices.isEmpty()) return null;
        
        Device minPriceDevice = devices.getFirst().get(0);
        for (ArrayList<Device> deviceList : devices) { //O(m)
            for (Device d : deviceList) {  //O(n)
                if (d.getPrice() < minPriceDevice.getPrice()) {
                    minPriceDevice = d;
                }
            }
        }
        return minPriceDevice;
    }

    /**
     * Sorts the devices by price and prints them out.
     *
     * This method sorts the devices by their prices in ascending order using a LinkedList,
     * then prints out each device's information including category, name, price, and quantity.
     * The index starts from 1 and is incremented for each printed device.
     *
     * @return A sorted linked list of devices by price.
     * @param No parameters.
     * @complexity Time Complexity: O(n log n), where n is the total number of devices across all categories.
     */
    public LinkedList<Device> sortedDeviceList() {
        LinkedList<Device> sortedList = new LinkedList<>();
        System.out.println("Devices sorted by price:");
        int index = 1; // Index starts from 1
        for (ArrayList<Device> deviceList : devices) {
            sortedList.addAll(deviceList);
        }
        sortedList.sort(Comparator.comparingDouble(Device::getPrice));
        
        // Print sorted devices
        for(Device d : sortedList){
            System.out.println(index + ". Category: " + d.getCategory() + ", Name: " + d.getName() + ", Price: " + d.getPrice() + "$, Quantity: " + d.getQuantity());
            index++; // Increment index after printing each device
        }
        return sortedList;
    }
    
    
    /**
     * Restocks or removes stock for a specific device in the inventory.
     * 
     * This method prompts the user to enter the name of the device to restock,
     * then prompts for the restock command (add or remove), and finally updates the quantity of the specified device accordingly.
     * 
     * @return No return value.
     * @param No parameters.
     * @complexity Time Complexity: O(m * n), where m is the number of device categories and n is the total number of devices.
     */
    public void restockDevice() {
        // Get the name of the device from the user
        System.out.println("Enter the name of the device to restock: ");
        String inputName = scanner.nextLine();
    
        // Get the restock command (Add/Remove) from the user
        System.out.println("Do you want to add or remove stock? (Add/Remove): ");
        String inputCommand = scanner.nextLine();
    
        // Check if the device exists
        boolean found = false;
        for (ArrayList<Device> deviceList : devices) {
            for (Device d: deviceList) {
                if (d.getName().equalsIgnoreCase(inputName)) {
                    found = true;
                    if (inputCommand.equalsIgnoreCase("Add")) {
                        System.out.println("Enter the quantity to add: ");
                        int inputQuantity = scanner.nextInt();
                        d.setQuantity(d.getQuantity() + inputQuantity);
                        System.out.println(inputQuantity + " units added to the stock of " + inputName);
                    }
                    else if (inputCommand.equalsIgnoreCase("Remove")) {
                        System.out.println("Enter the quantity to remove: ");
                        int inputQuantity = scanner.nextInt();
                        if (d.getQuantity() >= inputQuantity) {
                            d.setQuantity(d.getQuantity() - inputQuantity);
                            System.out.println(inputQuantity + " units removed from the stock of " + inputName);
                        } 
                        else {
                            System.out.println("Insufficient stock to remove.");
                        }
                    } else {
                        System.out.println("Invalid command. Please enter 'Add' or 'Remove'.");
                    }
                }
            }
        }
    
        // Notify the user if the device is not found
        if (!found) {
            System.out.println("Device not found.");
        }
    }
    

    /**
     * Exports an inventory report.
     * 
     * This method generates and prints an inventory report, including details such as category, name, price, and quantity of each device,
     * along with the total number of devices and the total inventory value.
     * 
     * @return No return value.
     * @param No parameters.
     * @complexity Time Complexity: O(m * n), where m is the number of device categories and n is the total number of devices.
     */
    public void exportInventoryReport() {
        double totalPrice = 0.0;
        int index = 1;
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = currentDate.format(formatter);
        String currentDateAsString = "Generated on: " + formattedDate;
        System.out.println("Electronics Shop Inventory Report");
        System.out.println(currentDateAsString);
        System.out.println("----------------------------------------");
        System.out.println("| No | Category | Name | Price | Quantity |");
        System.out.println("----------------------------------------");

        for (ArrayList<Device> deviceList : devices) {
            for (Device d: deviceList) {
                System.out.println(" | "+ index +" | " + d.getCategory() + " | " + d.getName() + " | $" + d.getPrice() + " | " + d.getQuantity());
                index++;
                totalPrice += d.getPrice() * d.getQuantity();
            }
        }
        System.out.println("----------------------------------------");
        System.out.println("Summary:" + "\n-Total Number of Devices: " + (index - 1) + "\n-Total Inventory Value: " + "$" + totalPrice + "\n" + "\nEnd of Report");
    }

    /**
     * Calculates the total value of the inventory.
     * 
     * This method iterates through each device in the inventory and calculates the total value
     * by multiplying the price of each device with its quantity, then summing up all the values.
     * 
     * @return The total value of the inventory.
     * @param No parameters.
     * @complexity Time Complexity: O(m * n), where m is the number of device categories and n is the total number of devices.
     */
    public double calculateTotalInventoryValue() {
        double totalValue = 0.0;
        for (ArrayList<Device> deviceList : devices) {
            for (Device device : deviceList) {
                totalValue += device.getPrice() * device.getQuantity();
            }
        }
        return totalValue;
    }
    
}