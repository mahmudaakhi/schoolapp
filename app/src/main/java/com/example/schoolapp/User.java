package com.example.schoolapp;

public class User {
    private String name, phone, fathername,roll,cls;

    public User() {

    }

    public User(String name, String phone, String fathername,String roll,String cls) {
        this.name = name;
        this.phone = phone;
        this.fathername = fathername;
        this.roll = roll;
        this.cls = cls;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername= fathername;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String address) {
        this.roll = roll;
    }
    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

}
