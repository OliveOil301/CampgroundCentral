package main.java.Database;

import main.java.Camping.Site;
import main.java.Utilities.CSVManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SiteData {



    private static Database db;
    static Connection conn = null;

    public SiteData() {
        connect();
        driver();

    }

    public static void driver() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (Exception e) {
            try{
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            }catch (Exception d){{
                d.printStackTrace();
                System.out.println("Driver registration failed");
            }}
        }
    }

    public static void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:derby:CCDB;create=true");
            conn.setAutoCommit(true);
        } catch (Exception e) {
//            try {
//                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//                conn = DriverManager.getConnection(url);
//            }catch (Exception b){
//                System.out.println("Connection to embedded failed");
//                b.printStackTrace();
//            }
//            System.out.println("Connection to remote failed");
            e.printStackTrace();
        }
    }

    public ArrayList<Site> getSitesByGroup(String groupName){
        ArrayList<Site> sites = new ArrayList<>();



        return sites;
    }


    public void addSite(String siteName, String siteType) {
        try {
            String str =
                    "insert into Sites (siteName, siteType) values (?,?)";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, siteName);
            ps.setString(2, siteType);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add site to database with " + siteName + " as name and " + siteType + " as type.");
            return;
        }
        return;
    }

    public void loadSitesFromCSV() throws IOException {
        CSVManager CSVM = new CSVManager("src/main/resources/storage/Sites.csv");
        ArrayList<String[]> siteCSV = CSVM.readWholeCSV();
        for (String[] s:siteCSV) {
            addSite(s[0] + s[1], s[2]);
        }
        System.out.println("WE LOADED EVERYTHING!!");
    }


}
