package main.java.Database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class Database {



    static Connection conn = null;
    // Url for live code
    protected final static String url = "jdbc:derby:CCDB;";
    // Url for testing
    private final static String testURL = "jdbc:derby://localhost:1527/CCDB;create=true";

    /**
     *  Constructor for database that is used for DB
     */
    public Database() {
        driver();
        connect();
        //makeCSVDependant(false); not sure if I need this yet
        createTables();
    }

    public static boolean isTableEmpty() {
        try {
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet rs = dmd.getTables(null, "APP", "SITES", null);
            return !rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
            conn = DriverManager.getConnection(url);
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



    public static void createTables() {
        try {
            if (isTableEmpty()) {
                String tbl1 =
                        "create table Sites (siteGroup varchar(50) not null, siteName varchar(50) not null, siteType varchar(50) not null, primary key (siteName))";

                PreparedStatement ps1 = conn.prepareStatement(tbl1);
                ps1.execute();

                String tbl2 =
                        "create table Users (employeeID varchar(50) not null, name varchar(50), password varchar(100), email varchar(250), type varchar(50), phoneNumber varchar(100), deleted boolean, primary key(employeeID))";
                PreparedStatement ps2 = conn.prepareStatement(tbl2);
                ps2.execute();

                String tbl3 =
                        "create table Reservations (reservationID varchar(50) not null, startDate varchar(50) not null, endDate varchar(50) not null, siteName varchar(50) not null, customerName varchar(250) not null, phoneNumber varchar(100) not null, vehicleMake varchar(20) not null, vehicleModel varchar(20) not null, vehicleLicense varchar(20) not null, camperMake varchar(20), camperModel varchar(20), camperLicense varchar(20), primary key(reservationID))";
                PreparedStatement ps3 = conn.prepareStatement(tbl3);
                ps3.execute();
                //---------------------------------------------------
                //This is a table where information needed on startup is stored. Maybe....
//                String tbl7 =
//                        "create table Information (currentReservationNumber varchar(50) not null, name varchar(50), visitDate timestamp, visitReason varchar(250), deleted boolean, primary key(guestID))";
//                PreparedStatement ps7 = conn.prepareStatement(tbl7);
//                ps7.execute();


            }
        } catch (Exception e) {
            System.out.println("Table creation failed");
            e.printStackTrace();
        }
    }




}
