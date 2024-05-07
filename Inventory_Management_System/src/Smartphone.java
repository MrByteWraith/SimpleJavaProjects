import java.util.*;



/**
 * Represents a Smartphone device.
 * 
 * This class implements the Device interface and represents a Smartphone device in the inventory.
 * It stores information such as category, name, price, and quantity of the Smartphone.
 */
public class Smartphone implements Device {
    private String category;
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new Smartphone device with the specified name, price, and quantity.
     * 
     * @param name     The name of the Smartphone.
     * @param price    The price of the Smartphone.
     * @param quantity The quantity of the Smartphone in the inventory.
     */
    public Smartphone(String name, double price, int quantity) {
        this.category = "Smart Phone";
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
