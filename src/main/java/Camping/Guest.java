package main.java.Camping;

public class Guest {
    private String name;
    private String guestNumber;
    private ReservationID reservationID;
    private String phoneNumber;


    public Guest(String name, String guestNumber, ReservationID reservationID, String phoneNumber) {
        this.name = name;
        this.guestNumber = guestNumber;
        this.reservationID = reservationID;
        this.phoneNumber = phoneNumber;
    }


    public Guest(String name, String guestNumber, ReservationID reservationID) {
        this.name = name;
        this.guestNumber = guestNumber;
        this.reservationID = reservationID;
    }
}
