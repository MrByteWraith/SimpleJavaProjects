import java.util.*;


public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        ArrayList<Device> DeviceList = new ArrayList<>();
        Device samsungTV = new TV("Samsung TV", 1250.00, 15);
        DeviceList.add(samsungTV);
        Device iphone = new Smartphone("Iphone 15", 800.00, 20);
        DeviceList.add(iphone);
        Device asus = new Laptop("Asus TUF", 900.00, 13);
        DeviceList.add(asus);
        Device ipad = new Tablet("Ipad", 550.00, 9);
        DeviceList.add(ipad);
        Device speaker = new Speaker("Rampage", 220.00, 8);
        DeviceList.add(speaker);
        inventory.addDevice(DeviceList);

        while (true) {
            System.out.println("\nWelcome to the Electronics Inventory Management System!");
            System.out.println("\nPlease select an option:");
            System.out.println("1. Add new device");
            System.out.println("2. Remove device");
            System.out.println("3. Update device details");
            System.out.println("4. Display list of all devices");
            System.out.println("5. Find the cheapest device");
            System.out.println("6. Sort devices by price");
            System.out.println("7. Calculate total inventory value");
            System.out.println("8. Restock a device");
            System.out.println("9. Export inventory report");
            System.out.println("0. Exit\n");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add new device
                    System.out.println("Enter category name:");
                    String category = scanner.nextLine();
                    System.out.println("Enter device name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter price:");
                    double price = scanner.nextDouble();
                    System.out.println("Enter quantity:");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    // Create device based on category
                    Device newDevice;
                    switch (category) {
                        case "TV":
                            newDevice = new TV(name, price, quantity);
                            break;
                        case "Smart Phone":
                            newDevice = new Smartphone(name, price, quantity);
                            break;
                        case "Laptop":
                            newDevice = new Laptop(name, price, quantity);
                            break;
                        case "Tablet":
                            newDevice = new Tablet(name, price, quantity);
                            break;
                        case "Speaker":
                            newDevice = new Speaker(name, price, quantity);
                            break;
                        default:
                            System.out.println("Invalid category.");
                            continue; // Skip the rest of the loop
                    }

                    // Add the new device to inventory
                    ArrayList<Device> newDeviceList = new ArrayList<>();
                    newDeviceList.add(newDevice);
                    inventory.addDevice(newDeviceList);
                    System.out.println(category + ", " + name + ", " + price + "$, " + quantity + " amount added...");
                    break;

                case 2:
                    // Remove device
                    System.out.println("Enter the name of the device to remove: ");
                    String removeDeviceName = scanner.nextLine();
                    boolean removed = false;
                    for (ArrayList<Device> deviceList : inventory.devices) {
                        for (Device d : deviceList) {
                            if (d.getName().equalsIgnoreCase(removeDeviceName)) {
                                deviceList.remove(d);
                                removed = true;
                                System.out.println(removeDeviceName + " removed from the inventory.");
                                break;
                            }
                        }
                        if (removed) {
                            break;
                        }
                    }
                    if (!removed) {
                        System.out.println("Device not found.");
                    }
                    break;
                case 3:
                    // Update device details
                    System.out.println("Enter the name of the device to update: ");
                    String updateDeviceName = scanner.nextLine();
                    System.out.println("Enter new price (leave blank to keep current price): ");
                    String priceInput = scanner.nextLine();
                    double newPrice = priceInput.isEmpty() ? -1 : Double.parseDouble(priceInput);
                    System.out.println("Enter new quantity (leave blank to keep current quantity): ");
                    String quantityInput = scanner.nextLine();
                    int newQuantity = quantityInput.isEmpty() ? -1 : Integer.parseInt(quantityInput);
                
                    boolean updated = false;
                    for (ArrayList<Device> deviceList : inventory.devices) {
                        for (Device d : deviceList) {
                            if (d.getName().equalsIgnoreCase(updateDeviceName)) {
                                // Update price if provided
                                if (newPrice >= 0) {
                                    d.setPrice(newPrice);
                                }
                                // Update quantity if provided
                                if (newQuantity >= 0) {
                                    d.setQuantity(newQuantity);
                                }
                                updated = true;
                                System.out.println("Device details updated successfully.");
                                break;
                            }
                        }
                        if (updated) {
                            break;
                        }
                    }
                    if (!updated) {
                        System.out.println("Device not found.");
                    }
                    break;
                case 4:
                    // Display list of all devices
                    inventory.displayDevices();
                    break;
                case 5:
                    // Identify device with minimum price
                    Device minPriceDevice = inventory.findDeviceWithMinimumPrice();
                    if (minPriceDevice != null) {
                        System.out.println("Device with minimum price:");
                        System.out.println("Category: "+  minPriceDevice.getCategory() + ", Name:" + minPriceDevice.getName() + ", Price: " + minPriceDevice.getPrice() + "$ , Quantity: " + minPriceDevice.getQuantity());
                    } else {
                        System.out.println("Inventory is empty.");
                    }
                    break;
                case 6:
                    // Sort devices by price
                    inventory.sortedDeviceList();
                    break;
                case 7:
                    // Calculate total inventory value
                    double totalValue = inventory.calculateTotalInventoryValue();
                    System.out.println("Total inventory value: $" + totalValue);
                    break;
                case 8:
                    // Restock a device
                    inventory.restockDevice();
                    break;
                case 9:
                    // Export inventory report
                    inventory.exportInventoryReport();
                    break;
                case 0:
                    // Exit
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
            }
            scanner.close();
        }
        
    }
}