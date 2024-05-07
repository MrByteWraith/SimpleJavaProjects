import java.util.*;


/**
 * The Device interface represents a generic electronic device.
 * It provides methods to retrieve and modify device properties.
 */
interface Device {
    /**
     * Gets the category of the device.
     * 
     * @return The category of the device.
     */
    String getCategory();

    /**
     * Gets the name of the device.
     * 
     * @return The name of the device.
     */
    String getName();

    /**
     * Gets the price of the device.
     * 
     * @return The price of the device.
     */
    double getPrice();

    /**
     * Gets the quantity of the device.
     * 
     * @return The quantity of the device.
     */
    int getQuantity();

    /**
     * Sets the price of the device.
     * 
     * @param price The new price of the device.
     */
    void setPrice(double price);

    /**
     * Sets the quantity of the device.
     * 
     * @param quantity The new quantity of the device.
     */
    void setQuantity(int quantity);
}