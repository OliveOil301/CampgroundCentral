package main.java.Shopping;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<ShoppingItem> items;
    private ObservableList<ShoppingItem> itemsObservable;
    private Float totalPrice;
    private String listName;


    /**
     * Constructor with just the list of items.
     * This way, a cart can be generated directly with a list of items instead of
     * generated, then items added.
     * @param items is an ArrayList of ShoppingItem that represents the items in the shopping cart.
     */
    public ShoppingCart(ArrayList<ShoppingItem> items) {
        this.items = items;
        this.itemsObservable = FXCollections.observableList(items);
        this.totalPrice = generateTotalPrice(items);
    }

    public ShoppingCart() {
    }


    private float generateTotalPrice(ArrayList<ShoppingItem> items){
        float total = 0;
        if(!items.isEmpty()){
            for (ShoppingItem item:items) {
                total += item.getPrice() * item.getQuantity();
            }
        }
        return total;
    }

    public void addList(ArrayList<ShoppingItem> items) {
        this.items.addAll(items);
        this.itemsObservable.addAll(items);
        this.totalPrice = generateTotalPrice(this.items);
    }

    public void addItem(ShoppingItem item){

    }


}
