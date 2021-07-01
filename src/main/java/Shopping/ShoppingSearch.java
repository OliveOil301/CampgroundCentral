package main.java.Shopping;

import javafx.beans.property.SimpleListProperty;
import main.java.App;

import java.util.ArrayList;

public class ShoppingSearch {
    private String searchString;
    public SimpleListProperty<ShoppingItem> matchedItems;



    public void setSearchString(String s){
        this.searchString = s;
        findItems();//This replaces the item list with matched items
    }



    private void findItems(){
        matchedItems.clear();
        ArrayList<ShoppingItem> itemsToSearch = App.shoppingItemManager.getAllItems();
        for (ShoppingItem s:itemsToSearch) {
            if(s.getName().contains(searchString)){
                matchedItems.add(s);
            }
        }
    }



}
