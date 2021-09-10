package main.java.Database;

import main.java.App;
import main.java.Camping.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ReservationData extends Data {

    public ReservationData() {
        connect();
        driver();
    }

    public GroupManager loadAllReservations(GroupManager g) throws IOException {
        //order by siteGroup ASC
        String str = "select * from Reservations";
        ReservationID rID = null;
        try {
            Statement stmt = conn.createStatement();
            //Query to retrieve records
            //Executing the query
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                String[] reservationData = new String[12];
                reservationData[0] = rs.getString("reservationID");
                reservationData[1] = rs.getString("startDate");
                reservationData[2] = rs.getString("endDate");
                reservationData[3] = rs.getString("siteName");
                reservationData[4] = rs.getString("customerName");
                reservationData[5] = rs.getString("phoneNumber");
                reservationData[6] = rs.getString("vehicleMake");
                reservationData[7] = rs.getString("vehicleModel");
                reservationData[8] = rs.getString("vehicleLicense");
                reservationData[9] = rs.getString("camperMake");
                reservationData[10] = rs.getString("camperModel");
                reservationData[11] = rs.getString("camperLicense");

                ReservationID ID = new ReservationID(reservationData[0]);
                ArrayList<Guest> guestList = getGuestsFromID(ID);

                if (ID.isMoreThan(App.reservationID)) {
                    App.reservationID = ID;
                }

                Reservation r = new Reservation(ID, getDateFromString(reservationData[1]), getDateFromString(reservationData[2]), reservationData[3], getSplitName(reservationData[4]), reservationData[5], guestList, reservationData[6], reservationData[7], reservationData[8], reservationData[9], reservationData[10], reservationData[11]);
                App.groupManager.addReservation(r, false);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;


    }


    private LocalDate getDateFromString(String date) {
        // Date is in format dd/mm/yyyy with slashes
        int day;
        int month;
        int year;
        if(Integer.parseInt(date.substring(3, 4)) == 0){
            month = Integer.parseInt(date.substring(4, 5));
        } else {
            month = Integer.parseInt(date.substring(3, 5));
        }
        if(Integer.parseInt(date.substring(0, 1)) == 0){
            day = Integer.parseInt(date.substring(1, 2));
        } else {
            day = Integer.parseInt(date.substring(0, 2));
        }
        year = Integer.parseInt(date.substring(6, 10));

        return LocalDate.of(year, month, day);
    }

    private String getStringFromLocalDate(LocalDate date) {
        String day = Integer.toString(date.getDayOfMonth());
        String month = Integer.toString(date.getMonthValue());
        String year = Integer.toString(date.getYear());

        if (day.length() == 1) {
            day = "0" + day + "/";
        } else {
            day = day + "/";
        }

        if (month.length() == 1) {
            month = "0" + month + "/";
        } else {
            month = month + "/";
        }

        return day + month + year;

    }


    private String[] getSplitName(String name) {
        int lastCut = -1;
        int totalPieces = 0;
        String[] cutName = new String[10];
        for (int i = 0; i <= name.length() - 1; i++) {
            if (name.charAt(i) == ' ') {
                cutName[cutName.length - 1] = name.substring(lastCut + 1, i);
                lastCut = i;
                totalPieces++;
            }
        }

        return cutName;
    }


    private ArrayList<Guest> getGuestsFromID(ReservationID ID) {
        ArrayList<Guest> guestList = new ArrayList<>();

//        String str = "select * from Reservations where reservationID = ?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(str);
//            ps.setString(1, ID.getAlphanumericCode());
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                String[] Guest = new String[4];
//                Guest[0] = rs.getString("name");
//                Guest[1] = rs.getString("guestNumber");
//                Guest[2] = rs.getString("reservationID");
//                Guest[3] = rs.getString("phoneNumber");
//
//                Guest g = new Guest(Guest[0], Guest[1], new ReservationID(Guest[2]), Guest[3]);
//                guestList.add(g);
//            }
//            rs.close();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return guestList;
    }


    public void addReservationToDatabase(Reservation r){
        try {
            String str = "insert into Reservations (reservationID, startDate, endDate, siteName, customerName, phoneNumber, vehicleMake, vehicleModel, vehicleLicense, camperMake, camperModel, camperLicense) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, r.getReservationID().getAlphanumericCode());
            ps.setString(2, getStringFromLocalDate(r.getStartDate()));
            ps.setString(3, getStringFromLocalDate(r.getEndDate()));
            ps.setString(4, r.getSiteName());
            ps.setString(5, r.getCustomerName()[0] + " " + r.getCustomerName()[1]);
            ps.setString(6, r.getPhoneNumber());
            ps.setString(7, r.getVehicleMake());
            ps.setString(8, r.getVehicleModel());
            ps.setString(9, r.getVehicleLicense());
            ps.setString(10, r.getCamperMake());
            ps.setString(11, r.getCamperModel());
            ps.setString(12, r.getCamperLicense());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add edge");
        }
    }


    private void setLastReservationID(){

    }
}
