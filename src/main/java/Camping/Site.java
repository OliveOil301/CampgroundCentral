package main.java.Camping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

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

        if(this.availableDates == null){
            loadAvailableDates();
        }
        //This removes the dates of this reservation from the list of available days
        removeDates(r);

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

    /**
     * removeDates takes in a reservation and removes the dates of the reservation from the list of available dates for this site.
     * @param r is the reservation in question
     */
    private void removeDates(Reservation r){
        ArrayList<LocalDate> newAvailableDates = new ArrayList<LocalDate>();
        for (LocalDate d:this.availableDates) {
            LocalDate start = r.getStartDate();
            LocalDate end = r.getEndDate();

            if(start.isEqual(end)){
                if(!d.isEqual(start)){
                    newAvailableDates.add(d);
                }
            } else {
                while (!start.isEqual(end)) {
                    if (!d.isEqual(start)) {
                        newAvailableDates.add(d);
                    }
                    start = start.plusDays(1);
                }
            }
        }
        this.availableDates = newAvailableDates;
    }

    /**
     * isAvailable checks if this site is available along this stretch of dates. Returns true if available
     * @param start the start date for the reservation
     * @param end the end date for the reservation
     * @return true if the site is available for this stretch of days
     */
    public boolean isAvailable(LocalDate start, LocalDate end){
            if(start.isEqual(end)){
                for (LocalDate d:this.availableDates){
                    if(d.isEqual(start)){
                        return true;
                    }
                }
                return false;
            } else {
                while (!start.isEqual(end)) {
                    boolean haveHitAvailableDay = false;
                    for (LocalDate d:this.availableDates){
                        if(d.isEqual(start)){
                            haveHitAvailableDay = true;
                        }
                    }
                    if(!haveHitAvailableDay){
                        return false;
                    }
                    start = start.plusDays(1);
                }
                return true;
            }
    }

}
