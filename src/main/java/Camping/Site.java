package main.java.Camping;

import main.java.App;
import main.java.Exceptions.InvalidSiteException;

public class Site {
    private Group siteGroup;
    private Integer siteNumber;


    public Site(Group siteGroup, Integer siteNumber) throws InvalidSiteException {
        if(App.groupManager.containsGroup(siteGroup)){
            this.siteGroup = siteGroup;
            this.siteNumber = siteNumber;
        } else {
            throw new InvalidSiteException("The group you inputted does not exist. Please try again with a valid group.");
        }

    }
}
