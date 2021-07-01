package main.java.Shopping;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<ShoppingItem> items;
    private ObservableList<ShoppingItem> itemsObservable;
    private Double totalPrice;
    private String listName;


    /**
     * Constructor with just the list of items.
     * This way, a cart can be generated directly with a list of items instead of
     * generated, then items added.
     * @param items is an ArrayList of ShoppingItem that represents the items in the shopping cart.
     */
    public ShoppingCart(ArrayList<ShoppingItem> items) {
        this.items = items;
        this.totalPrice = this.generateTotalPrice();
        this.itemsObservable = FXCollections.observableList(items);
    }

    public ShoppingCart() {
    }


    private Double generateTotalPrice(){
        Double total = 0.0;
        if(!items.isEmpty()){
            for (ShoppingItem item:this.items) {
                total += item.getPrice() * item.getQuantity();
            }
        }
        return total;
    }

    public void addList(ArrayList<ShoppingItem> items) {
        this.items.addAll(items);
        this.totalPrice = this.generateTotalPrice();
        this.itemsObservable.addAll(items);
    }

    public void addItem(ShoppingItem item){
        this.items.add(item);
        this.totalPrice = this.generateTotalPrice();
        this.itemsObservable.add(item);

    }
    public void clearItems(){
        this.items.clear();
        this.totalPrice = 0.0;
        this.itemsObservable.clear();

    }


}
