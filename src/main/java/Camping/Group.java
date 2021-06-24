package main.java.Camping;

import main.java.Exceptions.InvalidGroupException;

import java.util.ArrayList;

public class Group {
    private String groupName;
    private ArrayList<Site> sitesInGroup;

    //Constructors here:---------------
    public Group(String groupName) {
        this.groupName = groupName;
        this.sitesInGroup = new ArrayList<Site>();
    }

    public Group(String groupName, ArrayList<Site> sitesInGroup) {
        this.groupName = groupName;
        this.sitesInGroup = sitesInGroup;
    }

    //Helper functions here:-----------

    /**
     * Checks if a site is alread in this group's list by comparing the names
     * @param s is the site you wish to match against the list of sites in this group.
     * @return true if the site is a duplicate, false if not.
     */
    private boolean duplicateSite(Site s){
        for (Site site:this.sitesInGroup) {
            if(site.getSiteName().equals(s.getSiteName())){
                return true;
            }
        }
        return false;
    }



    //Getters here:--------------------
    public String getGroupName(){
        return groupName;
    }

    public ArrayList<Site> getSitesInGroup(){
        return this.sitesInGroup;
    }

    public Integer getNumberOfSites(){
        return this.sitesInGroup.size();
    }


    //Setters below here:---------------
    public void setSitesInGroup(ArrayList<Site> sitesInGroup){
        this.sitesInGroup = sitesInGroup;
    }

    public void addSite(Site s) throws InvalidGroupException {
        if(duplicateSite(s)){
            throw new InvalidGroupException("The site you are trying to add is already in this group.\nPlease try another number.\nError message 246");
        } else{
            this.sitesInGroup.add(s);
        }
    }

}
