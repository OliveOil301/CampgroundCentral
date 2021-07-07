package main.java.Camping;

import java.time.LocalDate;
import java.util.ArrayList;

public class Site {
    private String siteName;
    private String siteType;
    private String groupName;
    private ArrayList<Reservation> listOfReservations;
    private ArrayList<LocalDate> availableDates;

    public Site( String groupName, String siteName, String siteType) {
        this.siteName = siteName;
        this.siteType = siteType;
        this.groupName = groupName;
    }

    public Site(String siteName, String siteType, ArrayList<Reservation> listOfReservations) {
        this.siteName = siteName;
        this.siteType = siteType;
        this.listOfReservations = listOfReservations;
        loadAvailableDates();
        //need to read a csv for the availableSiteTypes assignment here.
    }

    public Site(String siteName, ArrayList<Reservation> listOfReservations) {
        this.siteName = siteName;
        this.listOfReservations = listOfReservations;
        loadAvailableDates();
        //need to read a csv for the availableSiteTypes assignment here.

    }

    //Getters below here:-------------
    public String getSiteName() {
        return siteName;
    }

    public ArrayList<Reservation> getListOfReservations() {
        return listOfReservations;
    }


    public String getSiteType() {
        return siteType;
    }

    public String getGroupName() {
        return groupName;
    }

    //Setters below here:--------------
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void addReservation(Reservation r){
        this.listOfReservations.add(r);

        //This stuff below makes sure the reservedDates list gets updated when a reservation is added.
        //TODO: remake this part

    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }


    /**
     * loadReservedDates takes the list of reservations in this object and generates a list of available dates from it.
     * This makes creating new reservations and searching for available days much easier.
     * This loads available days 500 days ahead of the current day
     */
    private void loadAvailableDates(){
        ArrayList<LocalDate> reservedDates = new ArrayList<LocalDate>();
        for (Reservation r: this.listOfReservations) {
            LocalDate reservedDay = r.getStartDate();
            while(!reservedDay.isEqual(r.getEndDate())){
                reservedDates.add(reservedDay);
                reservedDay = reservedDay.plusDays(1);
            }
            reservedDates.add(r.getEndDate());
        }

        ArrayList<LocalDate> availableDates = new ArrayList<LocalDate>();
        LocalDate dateToEndOn = LocalDate.now().plusDays(500);
        LocalDate now = LocalDate.now();
        while(!now.isEqual(dateToEndOn)){
            for (LocalDate d:reservedDates) {
                if(!now.isEqual(d)){
                    availableDates.add(now);
                }
            }
        }

        this.availableDates = availableDates;
    }

    private void getReservableDates(LocalDate endingDay){
        //Make this return basically the inverse of the reservedDates list.
        return;

    }

}
