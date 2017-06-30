package developerworks.ajax.store;

import java.math.BigDecimal;
import java.util.*;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

/**
 * A very simple shopping Cart
 */
public class Cart {

    private HashMap<Item, Integer> contents;

    /**
     * Creates a new Cart instance
     */
    public Cart() {
        contents = new HashMap<Item, Integer>();
    }

    /**
     * Adds a named item to the cart
     *
     * @param itemName The name of the item to add to the cart
     */
    public void addItem(String itemCode) {
        // creates a new catalog object
        Catalog catalog = new Catalog();
        // checking if itemCode is present in the catalog or not
        if (catalog.containsItem(itemCode)) {
            // getting the item object from the catalog using itemCode
            Item item = catalog.getItem(itemCode);
            int newQuantity = 1;
            // if the item is already in the cart, addding 1 to the quantity
            if (contents.containsKey(item)) {
                Integer currentQuantity = contents.get(item);
                newQuantity += currentQuantity.intValue();
            }
            // if the item is not in the cart putting the quantity as 1 into the cart  or else changed quantity
            contents.put(item, new Integer(newQuantity));
        }
    }

    /**
     * Removes the named item from the cart
     *
     * @param itemName Name of item to remove
     */
    public void removeItems(String itemCode) {
        // creates a new catalog object
        Catalog catalog = new Catalog();
        int newQuantity;
        // checking if itemCode is present in the catalog or not
        if (catalog.containsItem(itemCode)) {
            // getting the item object from the catalog using itemCode
            Item item = catalog.getItem(itemCode);
            // if the item is already in the cart, addding 1 to the quantity
            if (contents.containsKey(item)) {
                Integer currentQuantity = contents.get(item);
                // subtracting 1 from the quantity of existing items
                newQuantity = currentQuantity.intValue() - 1;
                if (newQuantity > 0) {
                    contents.put(item, new Integer(newQuantity));
                } else {
                    //removing the items completely if quantity is 0
                    contents.remove(item);
                }
            }
        }
    }

    /**
     * @return Json representation of cart contents
     */
    public String toJson() {
        StringBuffer json = new StringBuffer();
        // adding the timestamp and total values to the Json string
        json.append("{\"cart\":{\"Generated\":").append(System.currentTimeMillis()).append(",\"Total\":\"").append(getCartTotal()).append("\",\"cartItems\":[");
        // iterating through the contents of the cart and adding them to the Json string
        for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
            Item item = I.next();
            // getting the quantity for that item
            int itemQuantity = contents.get(item);
            // adding code of the Item to Json
            json.append("{\"code\":\"").append(item.getCode()).append("\",");
            // adding name of the Item to Json
            json.append("\"name\":\"").append(item.getName()).append("\",");
            // adding quantity of the Item to Json
            json.append("\"quantity\":").append(itemQuantity).append("}");
            if (I.hasNext()) {
                json.append(",");
            }
        }
        json.append("]}}");
        // converting the stringbuffer to a string and returning it 
        return json.toString();
    }

    // this method is to get the total of the cart
    private String getCartTotal() {
        int total = 0;
        // creating an iterator for the cart and iterating through it
        for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
            Item item = I.next();
            // getting the quantity of the item
            int itemQuantity = contents.get(item);
            // calculating the total value of the cart (multiplying the quantity and their price)
            total += (item.getPrice() * itemQuantity);
        }
        //returing the total of the cart as a string value
        return "$" + new BigDecimal(total).movePointLeft(2);
    }
}