package com.example.djmlib;

/*
 *                       Copyright (c) Benjinn
 *
 *                            (C) Benjinn 2019
 * All rights are reserved. Reproduction in whole or in part is
 * prohibited without the written consent of the copyright owner.
 * Benjinn reserves the right to make changes without notice at any time.
 * Benjinn makes no warranty, expressed, implied or statutory, including but
 * not limited to any implied warranty of merchantability or fitness for any
 * particular purpose, or that the use will not infringe any third party patent,
 * copyright or trademark. Benjinn must not be liable for any loss or damage
 * arising from its use.
 */
class User {

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
