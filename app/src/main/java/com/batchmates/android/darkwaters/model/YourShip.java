package com.batchmates.android.darkwaters.model;

/**
 * Created by Android on 7/13/2017.
 */

public class YourShip {

    private String shipName;
    private String captainName;
    private String boatType;
    private int crew;
    private int weapons;

    public YourShip(String shipName, String captainName, String boatType, int crew, int weapons) {
        this.shipName = shipName;
        this.captainName = captainName;
        this.boatType = boatType;
        this.crew = crew;
        this.weapons = weapons;
    }


    public String getShipName() {
        return shipName;
    }

    public String getCaptainName() {
        return captainName;
    }

    public String getBoatType() {
        return boatType;
    }

    public int getCrew() {
        return crew;
    }

    public int getWeapons() {
        return weapons;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public void setBoatType(String boatType) {
        this.boatType = boatType;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public void setWeapons(int weapons) {
        this.weapons = weapons;
    }


}
