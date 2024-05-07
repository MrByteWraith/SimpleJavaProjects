import java.util.*;


/**
 * Represents a TV device.
 * 
 * This class implements the Device interface and represents a TV device in the inventory.
 * It stores information such as category, name, price, and quantity of the TV.
 */
public class TV implements Device {
    private String category;
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new TV device with the specified name, price, and quantity.
     * 
     * @param name     The name of the TV.
     * @param price    The price of the TV.
     * @param quantity The quantity of the TV in the inventory.
     */
    public TV(String name, double price, int quantity) {
        this.category = "TV";
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Retrieves the category of the TV.
     * 
     * @return The category of the TV.
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Retrieves the name of the TV.
     * 
     * @return The name of the TV.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Retrieves the price of the TV.
     * 
     * @return The price of the TV.
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Retrieves the quantity of the TV.
     * 
     * @return The quantity of the TV.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the price of the TV.
     * 
     * @param price The new price of the TV.
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the quantity of the TV.
     * 
     * @param quantity The new quantity of the TV.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}