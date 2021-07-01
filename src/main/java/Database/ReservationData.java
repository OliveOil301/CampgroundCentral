package main.java.Database;

import main.java.Camping.GroupManager;
import main.java.Camping.Reservation;
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
        try{
            PreparedStatement ps = conn.prepareStatement(str);
            ResultSet rs = ps.executeQuery();
            Reservation r = new Reservation();
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


                Site s = new Site(reservationData[0], reservationData[1], reservationData[2]);
                g.addSite(s);
            }
            rs.close();
            ps.close();
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
}
