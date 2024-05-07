import java.util.*;

/**
 * Represents a Tablet device.
 * 
 * This class implements the Device interface and represents a Tablet device in the inventory.
 * It stores information such as category, name, price, and quantity of the Tablet.
 */
public class Tablet implements Device {
    private String category;
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new Tablet device with the specified name, price, and quantity.
     * 
     * @param name     The name of the Tablet.
     * @param price    The price of the Tablet.
     * @param quantity The quantity of the Tablet in the inventory.
     */
    public Tablet(String name, double price, int quantity) {
        this.category = "Tablet";
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