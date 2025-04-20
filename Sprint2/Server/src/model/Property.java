package model;

import java.util.Date;

public class Property
{
    private int id;
    private String location;
    private double pricePerNight;
    private Facilities facilities;

    public Property(int id, String location, double pricePerNight, Facilities facilities) {
        this.id = id;
        this.location = location;
        this.pricePerNight = pricePerNight;
        this.facilities = facilities;
    }

    public int getId()
    {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Facilities getFacilities() {
        return facilities;
    }

    public String toString(){
        return "id: "+ id +", Location: " + location + ", Price per night: " + pricePerNight + ", Facilities: " + facilities.toString();
    }
}