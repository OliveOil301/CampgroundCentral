package main.java.Shopping;

public class ShoppingItem {
    private String name;
    private String description;
    private Float price;
    private String pricePerWhat;
    private Float quantity;
    private Double discount;


    public ShoppingItem(String name, String description, Float price, String pricePerWhat, Double discount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.pricePerWhat = pricePerWhat;
        this.discount = discount;
    }

    /**
     * An alternate constructor without a discount if the item to be created doesn't have one yet.
     * @param name is name of the item
     * @param description is the description of the item. Keep it short!
     * @param price is the price of the item in dollars per the next field
     * @param pricePerWhat is what the item is measured in, pounds, items, cubic feet, etc.
     */
    public ShoppingItem(String name, String description, Float price, String pricePerWhat) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.pricePerWhat = pricePerWhat;
        this.discount = 0.00;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public String getPricePerWhat() {
        return pricePerWhat;
    }

    public Float getQuantity() {
        return quantity;
    }
}
