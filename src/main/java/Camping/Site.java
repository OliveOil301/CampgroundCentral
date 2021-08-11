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
        this.listOfReservations = new ArrayList<Reservation>();
    }

    public Site(String siteName, String siteType, ArrayList<Reservation> listOfReservations) {
        this.siteName = siteName;
        this.siteType = siteType;
        this.listOfReservations = listOfReservations;
        //need to read a csv for the availableSiteTypes assignment here.
    }

    public Site(String siteName, ArrayList<Reservation> listOfReservations) {
        this.siteName = siteName;
        this.listOfReservations = listOfReservations;
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

    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }






    /**
     * isAvailable checks if this site is available along this stretch of dates. Returns true if available
     * @param start the start date for the reservation
     * @param end the end date for the reservation
     * @return true if the site is available for this stretch of days
     */
    public boolean isAvailable(LocalDate start, LocalDate end){
        if(listOfReservations == null){
            return true;
        }
        for (Reservation r:this.listOfReservations){
            LocalDate D = r.getStartDate();
            while(D.isBefore(r.getEndDate()) || D.isEqual(r.getEndDate())) {
                while(start.isBefore(end) || start.isEqual(end)){
                    if(D.isEqual(start)){
                        return false;
                    }
                    start = start.plusDays(1);
                }
                D = D.plusDays(1);
            }
        }
        return true;
    }

}
