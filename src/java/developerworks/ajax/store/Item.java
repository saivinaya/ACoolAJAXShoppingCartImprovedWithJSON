package developerworks.ajax.store;

import java.math.BigDecimal;

/**
 *
 * @author VinayaSaiD
 */
public class Item {

    private String code;
    private String name;
    private String description;
    private int price;

    /**
     *
     * @param code itemCode
     * @param name name of the item
     * @param description description of the item
     * @param price price of the item
     */
    public Item(String code, String name, String description, int price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     *
     * @return code of the item
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @return name of the item
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return price of the item
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @return price of the item as a string
     */
    public String getFormattedPrice() {
        return "$" + new BigDecimal(price).movePointLeft(2);
    }

    // checks two or more items and returns true if both have same itemCode
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (this == null) {
            return false;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        return ((Item) o).getCode().equals(this.code);
    }
}