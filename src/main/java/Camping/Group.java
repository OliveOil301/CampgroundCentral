package main.java.Camping;

public class Group {
    private String groupName;
    private Integer numberOfSites;

    public Group(String groupName) {
        this.groupName = groupName;
        this.numberOfSites = 1;
    }

    public Group(String groupName, Integer numberOfSites) {
        this.groupName = groupName;
        this.numberOfSites = numberOfSites;
    }

    public String getgroupName(){
        return groupName;
    }

    public void setNumberOfSites(Integer numberOfSites){
        this.numberOfSites = numberOfSites;
    }

    public Integer getNumberOfSites(){
        return this.numberOfSites;
    }
}
