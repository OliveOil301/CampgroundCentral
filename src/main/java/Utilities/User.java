package main.java.Utilities;

public class User {
    private String[] name;
    private String employeeID;
    private String password;
    private String phoneNumber;
    private String email;
    private Role role;
    private Boolean deleted;


    public User(String[] name, String employeeID, String password, String phoneNumber, String email, Role role) {
        this.name = name;
        this.employeeID = employeeID;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }


    //Getters for all object variables below here------------------------
    public String[] getName() {
        return name;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    //Setters for all object variables below here-----------------------
    public void setName(String[] name) {
        this.name = name;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
