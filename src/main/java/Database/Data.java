package main.java.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Data {
    static Connection conn = null;



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
            conn = DriverManager.getConnection("jdbc:derby:CCDB;");
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
}
