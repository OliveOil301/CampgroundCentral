package main.java.Camping;

import java.time.LocalDate;
import java.util.ArrayList;

public class Site {
    private String siteName;
    private String siteType;
    private String groupName;
    private ArrayList<Reservation> listOfReservations;
    private ArrayList<LocalDate> reservedDates;

    public Site( String groupName, String siteName, String siteType) {
        this.siteName = siteName;
        this.siteType = siteType;
        this.groupName = groupName;
    }

    public Site(String siteName, String siteType, ArrayList<Reservation> listOfReservations) {
        this.siteName = siteName;
        this.siteType = siteType;
        this.listOfReservations = listOfReservations;
        loadReservedDates();
        //need to read a csv for the availableSiteTypes assignment here.
    }

    public Site(String siteName, ArrayList<Reservation> listOfReservations) {
        this.siteName = siteName;
        this.listOfReservations = listOfReservations;
        loadReservedDates();
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
        LocalDate reservedDay = r.getStartDate();
        while(!reservedDay.isEqual(r.getEndDate())){
            this.reservedDates.add(reservedDay);
            reservedDay = reservedDay.plusDays(1);
        }
        this.reservedDates.add(r.getEndDate());

    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }


    /**
     * loadReservedDates takes the list of reservations in this object and generates a list of reserved dates from it.
     * This makes creating new reservations and searching for available days much easier.
     */
    private void loadReservedDates(){
        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        for (Reservation r: this.listOfReservations) {
            LocalDate reservedDay = r.getStartDate();
            while(!reservedDay.isEqual(r.getEndDate())){
                dates.add(reservedDay);
                reservedDay = reservedDay.plusDays(1);
            }
            dates.add(r.getEndDate());
        }
        this.reservedDates = dates;
    }

    private void getReservableDates(LocalDate endingDay){
        //Make this return basically the inverse of the reservedDates list.
        return;

    }

}
