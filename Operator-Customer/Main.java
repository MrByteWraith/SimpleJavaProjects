import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Main {
    private static int numberofitems(String key_word) {
        switch (key_word) {
            case "order":
                return 6;
            case "retail_customer":
                return 7;
            case "corporate_customer":
                return 8;
            case "operator":
                return 7;
            default:
                return -1;
        }
    }

  

    public static void main(String[] args) {
        
        try {
            File data_file = new File(PATH_OF_FILE);
            Scanner sc = new Scanner(data_file);
            Scanner inputsc = new Scanner(System.in);
            System.out.println("Please enter your ID...");  
            int ID_input = inputsc.nextInt();

            int OrderCnt = 0;
            int RetailCustomerCnt = 0;
            int CorporateCustomerCnt = 0;
            int OperatorCnt = 0;
            int xCnt = 0;
            int xFound = 0;


            
            int CustomerOrderFound=0;
            int CustomerFound=0;


            Operator[] Operators = new Operator[100];
            Retail_customer[] Retail_customers = new Retail_customer[100];
            Corporate_customer[] Corporate_customers = new Corporate_customer[100];
            Order[] Orders = new Order[100];



            while (sc.hasNextLine()) {
                String data_row = sc.nextLine();
                String[] data_pieces = data_row.split(";");

                String key_word = data_pieces[0];
                if (data_pieces.length != numberofitems(key_word)) {
                    continue;
                }
                if (key_word.equals("order")) {
                    String product_name = data_pieces[1];
                    int count = Integer.parseInt(data_pieces[2]);
                    int total_price = Integer.parseInt(data_pieces[3]);
                    int status = Integer.parseInt(data_pieces[4]);
                    int customer_ID = Integer.parseInt(data_pieces[5]);
                    Orders[OrderCnt] = new Order(product_name, count, total_price, status, customer_ID);
                    OrderCnt++;
                    
                } else if (key_word.equals("retail_customer")) {
                    String name = data_pieces[1];
                    String surname = data_pieces[2];
                    String address = data_pieces[3];
                    String phone = data_pieces[4];
                    int customer_ID = Integer.parseInt(data_pieces[5]);
                    int operator_ID = Integer.parseInt(data_pieces[6]);
                    xFound = 0;
                    for (xCnt=0; xCnt<RetailCustomerCnt ; ++xCnt) {
                        if (Retail_customers[xCnt].ID==customer_ID) {
                            xFound = 1;
                            break;
                        }
                    }

                    if (customer_ID >= 0 && xFound != 1) {
                        Retail_customers[RetailCustomerCnt] = new Retail_customer(name, surname, address, phone, customer_ID, operator_ID);
                        RetailCustomerCnt++;
                    }

                } else if (key_word.equals("corporate_customer")) {
                    String name = data_pieces[1];
                    String surname = data_pieces[2];
                    String address = data_pieces[3];
                    String phone = data_pieces[4];
                    int customer_ID = Integer.parseInt(data_pieces[5]);
                    int operator_ID = Integer.parseInt(data_pieces[6]);
                    String company_name = data_pieces[7];
                    xFound = 0;
                    for (xCnt=0; xCnt<CorporateCustomerCnt ; ++xCnt) {
                        if (Corporate_customers[xCnt].ID==customer_ID) {
                            xFound = 1;
                            break;
                        }
                    }
                    if (xFound != 1 && customer_ID >= 0) {
                        Corporate_customers[CorporateCustomerCnt] = new Corporate_customer(name, surname, address, phone, customer_ID, operator_ID, company_name);
                        CorporateCustomerCnt++;
                    }
                } else if (key_word.equals("operator")) {
                    String name = data_pieces[1];
                    String surname = data_pieces[2];
                    String address = data_pieces[3];
                    String phone = data_pieces[4];
                    int ID = Integer.parseInt(data_pieces[5]);
                    int wage = Integer.parseInt(data_pieces[6]);
                    xFound = 0;
                    for (xCnt=0; xCnt<OperatorCnt ; ++xCnt) {
                        if (Operators[xCnt].ID==ID) {
                            xFound = 1;
                       
                            break;
                        }
                    }
                    if (xFound != 1 && ID >= 0) {
                        Operators[OperatorCnt] = new Operator(name, surname, address, phone, ID, wage);
                        OperatorCnt++;
                        
                    }

                } else {
                    continue;
                }
            }
            sc.close();
            inputsc.close();
            int found = 0;
            
            for (int x = 0; x < OperatorCnt; ++x) 
            {
                if (ID_input == Operators[x].ID) 
                {
                    Operators[x].print_operator();
                    for (xCnt=0; xCnt<RetailCustomerCnt ; ++xCnt) 
                    {
                        if (Retail_customers[xCnt].operator_ID==Operators[x].ID)
                        {
                        CustomerFound++;
                        Operators[x].define_customers(new Customer[]{Retail_customers[xCnt]});
                        Operators[x].print_customers();
                        for (int yCnt=0; yCnt<OrderCnt ; ++yCnt) 
                        {
                            if (Orders[yCnt].customer_ID==Retail_customers[xCnt].ID) 
                            {
                                CustomerOrderFound++;
                                Retail_customers[xCnt].define_orders(new Order[]{Orders[yCnt]});
                                Retail_customers[xCnt].print_orders();
                             }
                             
                        }
                        System.out.println("--------------------------\n");
                        
                        }
                    }                  

                for (xCnt=0; xCnt<CorporateCustomerCnt ; ++xCnt) {
                
                    if (Corporate_customers[xCnt].operator_ID==Operators[x].ID) 
                    {
                    CustomerFound++;
                    Operators[x].define_customers(new Customer[]{Corporate_customers[xCnt]});
                    Operators[x].print_customers();
                    for (int yCnt=0; yCnt<OrderCnt ; ++yCnt) {
                        if (Orders[yCnt].customer_ID==Corporate_customers[xCnt].ID) {
                            CustomerOrderFound++;
                            Corporate_customers[xCnt].define_orders(new Order[]{Orders[yCnt]});
                            Corporate_customers[xCnt].print_orders();
                         }
                    }
                    
                    }
                    }                  


                        found = 1;
                        break;
                }
            }




            for (xCnt=0; xCnt<RetailCustomerCnt ; ++xCnt) {
                
                if (Retail_customers[xCnt].ID==ID_input) {
                 found=1;   
                 CustomerFound++;
                 Retail_customers[xCnt].print_customer();
                 for (int yCnt=0; yCnt<OrderCnt ; ++yCnt) {
                    if (Orders[yCnt].customer_ID==Retail_customers[xCnt].ID) {
                        CustomerOrderFound++;
                        Retail_customers[xCnt].define_orders(new Order[]{Orders[yCnt]});
                        Retail_customers[xCnt].print_orders();
                        
                    }
                }

                 
                }
            }                  



            for (xCnt=0; xCnt<CorporateCustomerCnt ; ++xCnt) {
                
                if (Corporate_customers[xCnt].ID==ID_input) {
                 found=1;   
                 CustomerFound++;
                 Corporate_customers[xCnt].print_customer();
                 
                 for (int yCnt=0; yCnt<OrderCnt ; ++yCnt) {
                    if (Orders[yCnt].customer_ID==Corporate_customers[xCnt].ID) {
                        CustomerOrderFound++;
                        Corporate_customers[xCnt].define_orders(new Order[]{Orders[yCnt]});
                        Corporate_customers[xCnt].print_orders();
                        
                    }
                    
                }

              
                }
            }                  
            if (found == 0) {
                System.out.println("No operator/customer was found with " + ID_input + ". Please try again.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
    static class Person {
        protected String name;
        protected String surname;
        protected String address;
        protected String phone;
        protected int ID;

        public Person(String name, String surname, String address, String phone, int ID) {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.phone = phone;
            this.ID = ID;
        }
    }

    static class Order {
        protected String product_name;
        protected int count;
        protected int total_price;
        protected int status;
        protected int customer_ID;
        private static int orderindex = 1;

        public Order(String product_name, int count, int total_price, int status, int customer_ID) {
            this.product_name = product_name;
            this.count = count;
            this.total_price = total_price;
            this.status = status;
            this.customer_ID = customer_ID;
        }

        public String getStatusString() {
            switch (status) {
                case 0:
                    return "Initialized";
                case 1:
                    return "Processing";
                case 2:
                    return "Completed";
                case 3:
                    return "Cancelled";
                default:
                    return "Unknown";
            }
        }
        public void print_order()
        {
            System.out.println("Order #" + orderindex++ + " => " + "Product name: " + product_name + " - Count: " + count + " - Total price: " + total_price + " - Status: " + getStatusString() + ".");
           
        }
    }

    static class Operator extends Person {
        protected int wage;
        protected Customer[] customers;
        private static int customerNumber = 1;

        public Operator(String name, String surname, String address, String phone, int ID, int wage) {
            super(name, surname, address, phone, ID);
            this.wage = wage;
        }
  

        public void print_operator() {
            System.out.println("*** Operator Screen ***" + "\n--------------------------");
            System.out.println("Name & Surname: " + name +" "+ surname);
            System.out.println("Address: " + address);
            System.out.println("Phone: " + phone);
            System.out.println("ID: " + ID);
            System.out.println("Wage: " + wage);
            System.out.println("\n--------------------------");
        }
        public void define_customers(Customer[] customers)
        {
            this.customers = customers;
        }

        public void print_customers() 
        {  
            if (customers != null && customers.length > 0) {
                
                for (Customer customer : customers) {
                    if (customer instanceof Corporate_customer) {
                        System.out.println("Customer #" + customerNumber + "(a corporate customer)" + ":");
                    } 
                    else if (customer instanceof Retail_customer) 
                    {
                        System.out.println("Customer #" + customerNumber + "(a retail customer)" + ":");
                    }
                    System.out.println("Name & Surname: " + customer.name + " " + customer.surname);
                    System.out.println("Address: " + customer.address);
                    System.out.println("Phone: " + customer.phone);
                    System.out.println("ID: " + customer.ID);
                    System.out.println("Operator ID: " + customer.operator_ID);
                    if (customer instanceof Corporate_customer) {
                        Corporate_customer corporateCustomer = (Corporate_customer) customer;
                        System.out.println("Company Name: " + corporateCustomer.company_name);
                    }

                    customerNumber++;
                    System.out.println("--------------------------");
                }
            } 
            else 
            {
                System.out.println("This operator doesn't have any customer.");
            }
        }
    }

    static class Customer extends Person {
        protected int operator_ID;
        protected Order[] orders;

        public Customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
            super(name, surname, address, phone, ID);
            this.operator_ID = operator_ID;
        }

        public void define_orders(Order[] orders) {
            this.orders = orders;
        }

        public void print_customer() {
            System.out.println("*** Customer Screen ***");
            System.out.println("Name & Surname: " + name + " " + surname);
            System.out.println("Address: " + address);
            System.out.println("Phone: " + phone);
            System.out.println("ID: " + ID);
            System.out.println("Operator ID: " + operator_ID);
        }

        public void print_orders() {
            for (Order order : orders)
             {
                order.print_order();
            }
        }
    }

    static class Retail_customer extends Customer {
        public Retail_customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
            super(name, surname, address, phone, ID, operator_ID);
        }
    }

    static class Corporate_customer extends Customer {
        protected String company_name;

        public Corporate_customer(String name, String surname, String address, String phone, int ID, int operator_ID, String company_name) {
            super(name, surname, address, phone, ID, operator_ID);
            this.company_name = company_name;
        }

        @Override
        public void print_customer() {
            System.out.println("*** Customer Screen ***");
            System.out.println("Name & Surname: " + name + " " + surname);
            System.out.println("Address: " + address);
            System.out.println("Phone: " + phone);
            System.out.println("ID: " + ID);
            System.out.println("Operator ID: " + operator_ID);
            System.out.println("Company Name: " + company_name);
        
        }
    }


 
}