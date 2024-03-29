package main.java.Camping;

import main.java.App;
import main.java.Database.ReservationData;
import main.java.Exceptions.InvalidGroupException;
import main.java.Exceptions.InvalidSiteException;
import main.java.Utilities.CSVManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GroupManager {
    private ArrayList<Group> groups;

    public GroupManager() throws IOException {
        this.groups = new ArrayList<Group>();
    }


    public boolean containsGroup(Group potentialGroup){
        for (Group g:groups) {
            if(g.getGroupName().equals(potentialGroup.getGroupName())){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public Group getGroupFromString(String groupName){
        for (Group g:this.groups) {
            if(g.getGroupName().equals(groupName)){
                return g;
            }
        }
        return null;//If there isn't a group with that name
    }

    public void newGroup(Group g) throws InvalidGroupException {
        this.groups.add(g);
    }

    /**addSite(Site s) adds the site to the groupManager instance in the appropriate class
     * If there isn't a group with that site's group name, a new group is added with that site in it.
     *
     * @param s is the site to be added
     * @throws InvalidGroupException if a duplicate site is added to a group
     */
    public void addSite(Site s) throws InvalidGroupException {
        boolean siteAdded = false;
        for (Group g:this.groups) {
            if(g.getGroupName().equals(s.getGroupName())){
                g.addSite(s);
                siteAdded = true;
            }
        }
        if(!siteAdded){
            ArrayList<Site> sites = new ArrayList<>();
            sites.add(s);
            Group g = new Group(s.getGroupName(), sites);
            this.newGroup(g);
        }

    }

    /**
     * addReservation adds a reservation to it's respective site in the groupManager object
     * @param r
     */
    public void addReservation(Reservation r, boolean addTodatabase){
        for (Group g: this.groups) {
            if(g.getGroupName().equals(r.getSiteGroupName())){
                for (Site s:g.getSitesInGroup()) {
                    if(s.getSiteName().equals(r.getSiteName())){
                        s.addReservation(r);
                        if(addTodatabase){
                            App.reservationData.addReservationToDatabase(r);
                        }
                    }
                }
            }
        }
    }

    public Site getSiteFromString(String s) throws InvalidSiteException {
        for (Group g:this.groups) {
            for (Site site:g.getSitesInGroup()) {
                if(site.getSiteName().equals(s)){
                    return site;
                }
            }
        }
        throw new InvalidSiteException("This site does not exist");
    }






}
