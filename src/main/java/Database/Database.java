package main.java.Database;

import java.sql.*;

public class Database {



    private static Connection conn = null;
    // Url for live code
    private final static String url = "jdbc:derby:CCDB;create=true";
    // Url for testing
    private final static String testURL = "jdbc:derby://localhost:1527/BWdb;create=true";

    /**
     *  Constructor for database that is used for DB
     */
    private Database() {
        driver();
        connect();
        //makeCSVDependant(false); not sure if I need this yet
        createTables();
    }

    public static boolean isTableEmpty() {
        try {
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet rs = dmd.getTables(null, "APP", "NODES", null);
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
                        "create table Sites (siteName varchar(50) not null, siteType varchar(50) not null, primary key (siteName))";

                PreparedStatement ps1 = conn.prepareStatement(tbl1);
                ps1.execute();

                String tbl4 =
                        "create table Users (employeeID varchar(50) not null, name varchar(50), password varchar(100), email varchar(250), type varchar(50), phoneNumber varchar(100), deleted boolean, primary key(employeeID))";
                PreparedStatement ps4 = conn.prepareStatement(tbl4);
                ps4.execute();
                //---------------------------------------------------
                String tbl7 =
                        "create table Guests (guestID varchar(50) not null, name varchar(50), visitDate timestamp, visitReason varchar(250), deleted boolean, primary key(guestID))";
                PreparedStatement ps7 = conn.prepareStatement(tbl7);
                ps7.execute();
                // TODO : REQUEST WILL HAVE PARKING LOCATION
                String tblPatient =
                        "create table Patients (patientID varchar(50) not null, name varchar(50), userName varchar(100), password varchar(100), email varchar(250), type varchar(50), phoneNumber varchar(100), locationNodeID varchar(50) references Nodes, deleted boolean, providerName varchar(50), parkingLocation varchar(50), recommendedParkingLocation varchar(100), primary key(patientID))";
                PreparedStatement psPatient = conn.prepareStatement(tblPatient);
                psPatient.execute();

                //TODO : MOVE INTO ASSIGNMENTS
                String tblAppointments =
                        "create table Appointments(appointmentID varchar(50) not null , appointmentDate timestamp, appointmentType varchar(100), patientID varchar(50) references Patients, employeeID varchar(50) references Employees , primary key(appointmentID))";
                PreparedStatement psAppointments = conn.prepareStatement(tblAppointments);
                psAppointments.execute();

                String tbl5 = "create table Assignments(assignmentID varchar(50) not null, request varchar(50) references Requests, employeeID varchar(50), primary key(assignmentID))";
                PreparedStatement ps5 = conn.prepareStatement(tbl5);
                ps5.execute();

                String tbl6 = "create table Locations(locationID varchar(50) not null, request varchar(50) references Requests, nodeID varchar(50) references Nodes, primary key (locationID))";
                PreparedStatement ps6 = conn.prepareStatement(tbl6);
                ps6.execute();
                // TODO : Change from arrayList to String
                String permissionsInit = "create table Permissions(permissionID varchar(50) not null, edgeID varchar(50) references Edges, userType varchar(50), primary key(permissionID))";
                PreparedStatement psPerm = conn.prepareStatement(permissionsInit);
                psPerm.execute();

                String commentstbl = "create table Comments(request varchar(50) references Requests, title varchar(100), description varchar(500), author varchar(50), type varchar(50), created timestamp)";
                PreparedStatement commentStatement = conn.prepareStatement(commentstbl);
                commentStatement.execute();

            }
        } catch (Exception e) {
            System.out.println("Table creation failed");
            e.printStackTrace();
        }
    }




}
