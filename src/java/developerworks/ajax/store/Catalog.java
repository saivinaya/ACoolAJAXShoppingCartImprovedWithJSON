package developerworks.ajax.store;

import java.util.*;

/**
 * A Catalog object to know the items in the catalog
 *
 * @author VinayaSaiD
 */
public class Catalog {

    private static Map<String, Item> items;
    // items present in the catalog

    static {
        items = new HashMap<String, Item>();
        items.put("hat001", new Item("hat001", "Hat", "Stylish bowler hat (SALE!)", 1999));
        items.put("dog001", new Item("dog001", "Dog", "Chocolate labrador puppy", 7999));
        items.put("sou001", new Item("sou001", "Soup", "Can of tasty tomato soup", 199));
        items.put("cha001", new Item("cha001", "Chair", "Swivelling office chair", 4999));
        items.put("str001", new Item("str001", "String", "Metric tonne of bailing twine", 1999));
        items.put("qua001", new Item("qua001", "Quark", "Everyone's favorite sub-atomic particle", 49));
        // adding one more item to the catalog - Beer
        items.put("bee001", new Item("bee001", "Beer", "Good Beer for the party", 6));
    }

    /**
     *
     * @return all the items in the catalog
     */
    public Collection<Item> getAllItems() {
        return items.values();
    }

    /**
     *
     * @param itemCode
     * @return true if item present in the catalog else returns false
     */
    public boolean containsItem(String itemCode) {
        return items.containsKey(itemCode);
    }

    /**
     *
     * @param itemCode
     * @return gets the Item object for the matching itemCode
     */
    public Item getItem(String itemCode) {
        return items.get(itemCode);
    }
}
