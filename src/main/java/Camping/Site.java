package main.java.Camping;

import main.java.App;
import main.java.Exceptions.InvalidSiteException;

import java.util.ArrayList;

public class Site {
    //private Group siteGroup;
    private String Site;
    private ArrayList<Reservation> listOfReservations;

    public Site(String site, ArrayList<Reservation> listOfReservations) {
        Site = site;
        this.listOfReservations = listOfReservations;
    }

    public Site(String site) {
        Site = site;
    }
}
