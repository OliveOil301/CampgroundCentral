package main.java.Camping;

import main.java.App;
import main.java.Exceptions.InvalidSiteException;

import java.util.ArrayList;

public class Site {
    //private Group siteGroup;
    private String siteName;
    private ArrayList<Reservation> listOfReservations;

    public Site(String siteName, ArrayList<Reservation> listOfReservations) {
        this.siteName = siteName;
        this.listOfReservations = listOfReservations;
    }

    public Site(String siteName) {
        this.siteName = siteName;
    }

    //Getters below here:-------------
    public String getSiteName() {
        return siteName;
    }

    public ArrayList<Reservation> getListOfReservations() {
        return listOfReservations;
    }




    //Setters below here:--------------
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setListOfReservations(ArrayList<Reservation> listOfReservations) {
        this.listOfReservations = listOfReservations;
    }
}
