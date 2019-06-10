package com.example.eventregistration.Model;

public class Register {

   private String eventName,phone,teamName,regKey;

    public Register() {
    }

    public Register(String eventName, String phone, String teamName) {
        this.eventName = eventName;
        this.phone = phone;
        this.teamName = teamName;

    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getRegKey() {
        return regKey;
    }

    public void setRegKey(String regKey) {
        this.regKey = regKey;
    }
}
