package main.java.Shopping;

import java.util.ArrayList;

public class ShoppingItemManager {
    private ArrayList<ShoppingItem> allItems;



    public void addItem(ShoppingItem s){
        //Need to iterate through and check for duplicate names
        allItems.add(s);
    }

    public ArrayList<ShoppingItem> getAllItems() {
        return allItems;
    }
}
