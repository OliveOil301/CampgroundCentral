package main.java.Database;

import main.java.Camping.GroupManager;
import main.java.Camping.Reservation;
import main.java.Camping.Site;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
                String[] siteData = new String[12];
                siteData[0] = rs.getString("siteGroup");
                siteData[1] = rs.getString("siteName");
                siteData[2] = rs.getString("siteType");

                Site s = new Site(siteData[0], siteData[1], siteData[2]);
                g.addSite(s);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return g;


    }
}
