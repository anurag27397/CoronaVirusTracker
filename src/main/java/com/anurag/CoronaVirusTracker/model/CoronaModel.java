package com.anurag.CoronaVirusTracker.model;

public class CoronaModel {
    private String state;
    private String country;
    private int ConfirmedCase;

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    private int newCases;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getConfirmedCase() {
        return ConfirmedCase;
    }

    public void setConfirmedCase(int ConfirmedCase) {
        this.ConfirmedCase = ConfirmedCase;
    }

    @Override
    public String toString() {
        return "CoronaModel{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", ConfirmedCase=" + ConfirmedCase +
                '}';
    }
}
