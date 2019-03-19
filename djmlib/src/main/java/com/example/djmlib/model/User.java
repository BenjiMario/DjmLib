package com.example.djmlib.model;


public class User {

    public User(String fName, String lName, String mail){
        this.userFirstName = fName;
        this.userLastName = lName;
        this.userEmail = mail;
    }
    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private String userFirstName;
    private String userLastName;
    private String userEmail;

}
