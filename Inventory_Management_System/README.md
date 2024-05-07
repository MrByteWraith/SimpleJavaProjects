## Electronic Inventory Management System

This project is a simple Java application for managing the inventory of electronic devices. 
It allows users to perform various operations such as adding, removing, updating, listing devices, 
finding the cheapest device, sorting devices by price, calculating the total inventory value, restocking devices, and exporting an inventory report.


## Features

	1. Add Device: Users can add a new device to the inventory by providing details such as category, name, price, and quantity.
	2. Remove Device: Devices can be removed from the inventory by specifying their name.
	3. Update Device Details: Users can update the price and quantity of a device in the inventory.
	4. Display Devices: Lists all devices in the inventory along with their details.
	5. Find Cheapest Device: Identifies and displays the device with the lowest price in the inventory.
	6. Sort Devices by Price: Sorts all devices in the inventory by their prices in ascending order.
	7. Calculate Total Inventory Value: Computes the total value of all devices in the inventory.
	8. Restock Device: Allows users to add or remove stock for a specific device.
	9. Export Inventory Report: Generates and prints an inventory report including details of all devices and total inventory value.

## Usage

	1. Compile and run the "Main.java" file.
	2. Follow the on-screen menu to perform desired operations.


## Classes

	1. Main: Contains the main method to start the application and handle user inputs.
	2. Inventory: Manages the collection of devices and provides methods for various operations.
	3. Device: Interface representing a generic electronic device with methods to retrieve and modify device properties.
	4. TV, Smartphone, Laptop, Tablet, Speaker: Classes representing specific types of electronic devices, implementing the Device interface.


## Dependicies

Java 8 or higher.


## License

This project is licensed under the MIT License - see the LICENSE file for details.
