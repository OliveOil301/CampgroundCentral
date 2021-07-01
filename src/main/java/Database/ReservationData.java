package main.java.Database;

import main.java.App;
import main.java.Camping.GroupManager;
import main.java.Camping.Reservation;
import main.java.Camping.ReservationID;
import main.java.Camping.Site;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ReservationData extends Data{

    public ReservationData() {
        connect();
        driver();

    }

    public GroupManager loadAllReservations(GroupManager g) throws IOException {
        //order by siteGroup ASC
        String str = "select * from Reservations";
        ReservationID rID = null;
        try{
            PreparedStatement ps = conn.prepareStatement(str);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
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

                if(rID == null){
                    rID = new ReservationID(reservationData[0]);
                } else {
                    ReservationID rIDNew = new ReservationID(reservationData[0]);
                    if(rIDNew.isMoreThan(rID)){
                        rID = rIDNew;
                    }
                }

                Reservation r = new Reservation(new ReservationID(reservationData[0]), getDateFromString(reservationData[1]), getDateFromString(reservationData[2]), reservationData[3], getSplitName(reservationData[4]), reservationData[5], reservationData[6], reservationData[7], reservationData[8], reservationData[9], reservationData[10], reservationData[11]);
                //Need to make the GroupManager function that adds a reservation to their own site.
            }
            rs.close();
            ps.close();
            if(rID != null){
                App.reservationID = rID.plusOne();
            } else {
                App.reservationID = new ReservationID("000000");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return g;


    }



    private LocalDate getDateFromString(String date){
        // Date is in format dd/mm/yyyy with slashes
        return LocalDate.of(Integer.parseInt(date.substring(6,9)), Integer.parseInt(date.substring(3,4)), Integer.parseInt(date.substring(0,1)));
    }

    private String getStringFromLocalDate(LocalDate date){
        String day = Integer.toString(date.getDayOfMonth());
        String month = Integer.toString(date.getMonthValue());
        String year = Integer.toString(date.getYear());

        if(day.length()==1){
            day = "0" + day + "/";
        } else {
            day = day + "/";
        }

        if(month.length()==1){
            month = "0" + month + "/";
        } else {
            month = month + "/";
        }

        return day + month + year;

    }


    private String[] getSplitName(String name){
        int lastCut = -1;
        int totalPieces = 0;
        String[] cutName = new String[10];
        for (int i = 0; i< name.length()-1; i++) {
            if(name.charAt(i) == ' '){
                cutName[cutName.length-1] = name.substring(lastCut+1, i);
                lastCut = i;
                totalPieces ++;
            }
        }

        return cutName;
    }
}
