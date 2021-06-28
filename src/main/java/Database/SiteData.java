package main.java.Database;

import main.java.Camping.Site;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SiteData {



    private static Database db;
    static Connection conn = null;


    public ArrayList<Site> getSitesByGroup(String groupName){
        ArrayList<Site> sites = new ArrayList<>();



        return sites;
    }


    public int addSite(String node_id, double x, double y, String floor, String building, String node_type, String longname, String shortname) {
        try {
            String str =
                    "insert into Nodes (nodeID, xcoord, ycoord, floor, building, nodeType, longname, shortname, teamAssigned) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, node_id);
            ps.setDouble(2, x);
            ps.setDouble(3, y);
            ps.setString(4, floor);
            ps.setString(5, building);
            ps.setString(6, node_type);
            ps.setString(7, longname);
            ps.setString(8, shortname);
            ps.setString(9, "u");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add node");
            return 0;
        }
        return 1;
    }


}
