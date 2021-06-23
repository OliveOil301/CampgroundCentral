package main.java.Camping;

import main.java.Exceptions.InvalidGroupException;
import main.java.Utilities.CSVManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GroupManager {
    private ArrayList<Group> groups;

    public GroupManager() throws IOException {
        String line;
        String splitBy = ",";
        //parsing a CSV file into BufferedReader class constructor
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/storage/GroupsAndSites.csv"));

        //This is for filling up the Group list
        ArrayList<Group> listOfGroups = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] csvLine = line.split(splitBy);    // use comma as separator to split the group and amounts for the group
            Group newGroup = new Group(csvLine[0]);
            listOfGroups.add(newGroup);
        }

        this.groups = listOfGroups;
    }

    public boolean containsGroup(Group potentialGroup){
        for (Group g:groups) {
            if(g.getgroupName().equals(potentialGroup.getgroupName())){
                return true;
            }
        }
        return false;
    }


    public Group getGroupFromString(String groupName){
        for (Group g:this.groups) {
            if(g.getgroupName().equals(groupName)){
                return g;
            }
        }
        return null;//If there isn't a group with that name
    }

    public void newGroup(Group g) throws InvalidGroupException {
        CSVManager CSVM = new CSVManager("src/main/resources/storage/GroupsAndSites.csv");
        String[] Item = new String[2];
        Item[0] = g.getgroupName();
        Item[1] = String.valueOf(g.getNumberOfSites());


        if(this.containsGroup(g)){
            throw new InvalidGroupException("This group already exists. \nPlease modify it if you would like to change the number of sites. \nError Message 247");
        }
        try {
            CSVM.AddToEndOfCSV(Item);
        } catch(IOException e){
            throw new InvalidGroupException("The group could not be added. \nError Message 248");
        }
        this.groups.add(g);
    }
}
