package main.java.Camping;

import main.java.App;
import main.java.Exceptions.InvalidReservationIDException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reservation {
    private ReservationID reservationID;
    private LocalDate startDate;
    private LocalDate endDate;
    private String siteName;
    private String[] customerName;
    private String phoneNumber;
    private ArrayList<Guest> guestList;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleLicense;
    private String camperMake;
    private String camperModel;
    private String camperLicense;

    private ArrayList<String[]> guests;


    //Getters below here--------
    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String[] getCustomerName() {
        return customerName;
    }

    public String getSiteName() {
        return siteName;
    }

    public ReservationID getReservationID() {
        return reservationID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getVehicleLicense() {
        return vehicleLicense;
    }

    public String getCamperMake() {
        return camperMake;
    }

    public String getCamperModel() {
        return camperModel;
    }

    public String getCamperLicense() {
        return camperLicense;
    }

    public ArrayList<String[]> getGuests() {
        return guests;
    }


    //Setters below here--------------
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setCustomerName(String[] customerName) {
        this.customerName = customerName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense;
    }

    public void setCamperMake(String camperMake) {
        this.camperMake = camperMake;
    }

    public void setCamperModel(String camperModel) {
        this.camperModel = camperModel;
    }

    public void setCamperLicense(String camperLicense) {
        this.camperLicense = camperLicense;
    }

    public void setGuests(ArrayList<String[]> guests) {
        this.guests = guests;
    }

    //Constructors-----------------------------

    public Reservation() {

    }

    public Reservation(ReservationID reservationID, LocalDate startDate, LocalDate endDate, String siteName, String[] customerName, String phoneNumber, ArrayList<Guest> guestList, String vehicleMake, String vehicleModel, String vehicleLicense, String camperMake, String camperModel, String camperLicense) {
        this.reservationID = reservationID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.siteName = siteName;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.guestList = guestList;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleLicense = vehicleLicense;
        this.camperMake = camperMake;
        this.camperModel = camperModel;
        this.camperLicense = camperLicense;
    }


    //Additional methods--------------



    public void setToNextID() throws InvalidReservationIDException {
        App.reservationID = App.reservationID.plusOne();
        this.reservationID =  App.reservationID;
    }

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
        //also need to add to database
    }


    public void addGuests(ArrayList<String[]> newGuests){
        this.guests.addAll(newGuests);
        //also need to add to database
    }

    public String getSiteGroupName(){
        for (int i = 0; i <= this.siteName.length()-1; i++) {
            if(siteName.charAt(i) == ' '){
                return siteName.substring(0, i);
            }
        }
        return null;
    }




}
