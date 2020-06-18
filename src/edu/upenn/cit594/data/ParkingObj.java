package edu.upenn.cit594.data;

public class ParkingObj {
    private String state;
    private long fine;
    private int zipCode;

    public ParkingObj(int zipCode, long fine, String state) {
        this.zipCode = zipCode;
        this.fine = fine;
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public long getFine() {
        return fine;
    }

    public String getState() {
        return state;
    }
}