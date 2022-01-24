package com.example.sportin.model;

public class UserDetail {
    private String userName;
    private String professionSport;
    private String funSport;

    public UserDetail() {

    }

    public UserDetail( String userName, String professionSport){
        this.userName=userName;
        this.professionSport=professionSport;
    }

//    public UserDetail( String userName, String professionSport){
//        this.userName=userName;
//        this.professionSport=professionSport;
//    }

    public UserDetail( String userName){
        this.userName=userName;
    }

    public String getFunSport() {
        return funSport;
    }

    public String getProfessionSport() {
        return professionSport;
    }

    public String getUserName() {
        return userName;
    }
}
