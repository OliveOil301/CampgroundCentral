package main.java.Camping;

import main.java.Exceptions.InvalidGroupException;
import main.java.Utilities.CSVManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GroupManager {
    private ArrayList<Group> groups;

    public GroupManager() throws IOException {
//        String line;
//        String splitBy = ",";
//        //parsing a CSV file into BufferedReader class constructor
//        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/storage/GroupsAndSites.csv"));
//
//        //This is for filling up the Group list
//        ArrayList<Group> listOfGroups = new ArrayList<>();
//        while ((line = br.readLine()) != null) {
//            String[] csvLine = line.split(splitBy);    // use comma as separator to split the group and amounts for the group
//            Group newGroup = new Group(csvLine[0]);
//            listOfGroups.add(newGroup);
//        }
//
//        this.groups = listOfGroups;
    }

    public boolean containsGroup(Group potentialGroup){
        for (Group g:groups) {
            if(g.getGroupName().equals(potentialGroup.getGroupName())){
                return true;
            }
        }
        return false;
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
        if(siteAdded = false){
            ArrayList<Site> sites = new ArrayList<>();
            sites.add(s);
            Group g = new Group(s.getGroupName(), sites);
            newGroup(g);
        }

    }






}
