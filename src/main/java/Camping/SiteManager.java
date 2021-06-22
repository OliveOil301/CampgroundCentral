package main.java.Camping;

import main.java.Exceptions.InvalidSiteException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SiteManager {
    private ArrayList<Site> sites;
    private GroupManager groupManager = new GroupManager();

    public SiteManager() throws IOException, InvalidSiteException {
        String line;
        String splitBy = ",";
        //parsing a CSV file into BufferedReader class constructor
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/storage/Sites.csv"));

        //This is for filling up the Group list
        ArrayList<Site> listOfSites = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] csvLine = line.split(splitBy);// use comma as separator to split the group and amounts for the group
            for(int i = 1; i <= Integer.parseInt(csvLine[1]); i++){
                Site newSite = new Site(groupManager.getGroupFromString(csvLine[0]), i);
                listOfSites.add(newSite);
            }
        }
        //at this point, we've iterated through all the groups and all their respective sites so we add the list to the object variable.
        this.sites = listOfSites;
    }
}
