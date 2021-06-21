package main.java.Camping;

import java.util.ArrayList;

public class GroupManager {
    ArrayList<Group> groups;


    public boolean containsGroup(Group potentialGroup){
        for (Group g:groups) {
            if(g.getgroupName().equals(potentialGroup.getgroupName())){
                return true;
            }
        }
        return false;
    }
}
