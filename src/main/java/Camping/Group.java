package main.java.Camping;

import java.util.ArrayList;

public class Group {
    private String groupName;
    private ArrayList<Site> sitesInGroup;

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public Group(String groupName, ArrayList<Site> sitesInGroup) {
        this.groupName = groupName;
        this.sitesInGroup = sitesInGroup;
    }

    public String getGroupName(){
        return groupName;
    }

    public void setSitesInGroup(ArrayList<Site> sitesInGroup){
        this.sitesInGroup = sitesInGroup;
    }

    public ArrayList<Site> getSitesInGroup(){
        return this.sitesInGroup;
    }

    public Integer getNumberOfSites(){
        return this.sitesInGroup.size();
    }
}
