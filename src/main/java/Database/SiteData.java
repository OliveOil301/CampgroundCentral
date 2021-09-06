package main.java.Database;

import main.java.Camping.Group;
import main.java.Camping.GroupManager;
import main.java.Camping.Site;
import main.java.Utilities.CSVManager;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SiteData extends Data {



    public SiteData() {
        driver();
        connect();

    }




    public void addSite(String siteGroup, String siteName, String siteType) {
        try {
            String str =
                    "insert into Sites (siteGroup, siteName, siteType) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, siteGroup);
            ps.setString(2, siteName);
            ps.setString(3, siteType);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Failed to add site to database with " + siteName + " as name and " + siteType + " as type.");
            return;
        }
        return;
    }

    public void clearSites() throws SQLException {
        String str;
        Statement s = conn.createStatement();
        str = "delete from Sites";
        s.execute(str);
    }

    public void loadSitesFromCSV() throws IOException, SQLException {
        clearSites();

        CSVManager CSVM = new CSVManager("src/main/resources/storage/Sites.csv");
        ArrayList<String[]> siteCSV = CSVM.readWholeCSV();
        for (String[] s:siteCSV) {
            addSite(s[0], s[0] + " " + s[1], s[2]);
        }
        System.out.println("WE LOADED EVERYTHING!!");
    }

    public GroupManager getAllSites() throws IOException {
        GroupManager allSites = new GroupManager();
        //order by siteGroup ASC
        String str = "select * from Sites";
        try{
            PreparedStatement ps = conn.prepareStatement(str);
            ResultSet rs = ps.executeQuery();
            String group = null;
            while (rs.next()){
                String[] siteData = new String[3];
                siteData[0] = rs.getString("siteGroup");
                siteData[1] = rs.getString("siteName");
                siteData[2] = rs.getString("siteType");

                Site s = new Site(siteData[0], siteData[1], siteData[2]);
                allSites.addSite(s);
                System.out.println(s.getSiteName() + " was added to the groupManager");

            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return allSites;
    }


}
