## Operator And Customer Management System

This Java program is a system for managing operators and customers. Operators can view customers, list orders associated with customers, and more.


## Features

	**Operator Management:** Operators can view their own details including name, address, phone, ID, and wage.
	**Customer Management:** Operators can view both retail and corporate customers, along with their details.
	**Order Management:** Operators can view orders associated with each customer.
	**Error Handling:** The program includes error handling for file not found situations and incorrect user input.


## Getting Started

	1. Clone the Repository: git clone https://github.com/MrByteWraith/SimpleJavaProjects/Operator-Customer.git
	2. Compile and Run: 
		
		javac Main.java
		java Main

	3. Input ID: Upon running the program, you'll be prompted to enter an ID. Enter a valid ID to view associated informations.


## File Format

The program expects a specific file format for data input. 
The file should contain semicolon-separated values for each data entry, 
with each line representing a different entity (operator, customer, or order).

	Examples:
		order;<item_name>;<amount>;<customer_ID>
		retail_customer;<first_name>;<last_name>;<location>;<phone_number>;<customer_ID>;<operator_ID>
		corporate_customer;<first_name>;<last_name>;<location>;<phone_number>;<customer_ID>;<operator_ID>;<company_name>
		operator;<first_name>;<last_name>;<location>;<phone_number>;<operator_ID>;<wage>


## License

This project is licensed under the MIT License. See the LICENSE file for details.