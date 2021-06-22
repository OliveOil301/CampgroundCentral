package main.java.Camping;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reservation {
    private Float reservationNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private String[] Name;
    private Site reservationSite;
    private ArrayList<String[]> guests;


    //Getters below here--------
    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String[] getName() {
        return Name;
    }

    public Site getReservationSite() {
        return reservationSite;
    }

    public ArrayList<String[]> getGuests() {
        return guests;
    }


    //Setters beow here--------------
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setName(String[] name) {
        Name = name;
    }

    public void setReservationSite(Site reservationSite) {
        this.reservationSite = reservationSite;
    }

    public void setGuests(ArrayList<String[]> guests) {
        this.guests = guests;
    }


    //Additional methods--------------

    /** changeReservationByDays(int days)
     * adds or subtracts the inputted number of days from the current reservation
     * only does so if this will not cause a conflict with other reservations
     * @param days - the number of days to shift the reservation by. Can be positive, negative, or zero (won't do anything)
     */
    public void changeReservationByDays(int days){
        //This needs to still check if the entire reservation
        //can actually be moved that number of days.
        //If it can't then an exception must be thrown that the controller can handle.
    }

    public void addGuest(String[] newGuest){
        this.guests.add(newGuest);
    }


    public void addGuests(ArrayList<String[]> newGuests){
        this.guests.addAll(newGuests);
    }




}
