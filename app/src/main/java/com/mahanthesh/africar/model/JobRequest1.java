package com.mahanthesh.africar.model;

import java.io.Serializable;

public class JobRequest1 implements Serializable {
    private String duration;
    private String distance;
    private String rider_id;
    private String type;
    private String pickup_lat;
    private String pickup_long;
    private String drop_lat;
    private String drop_long;
    private String ride_fare;
    private boolean isAccepted;
    private boolean isVerified;
    private boolean isCompleted;
    private String OTP;

    public JobRequest1(String duration, String distance, String rider_id, String type, String pickup_lat, String pickup_long, String drop_lat, String drop_long, String ride_fare) {
        this.duration = duration;
        this.distance = distance;
        this.rider_id = rider_id;
        this.type = type;
        this.pickup_lat = pickup_lat;
        this.pickup_long = pickup_long;
        this.drop_lat = drop_lat;
        this.drop_long = drop_long;
        this.ride_fare = ride_fare;
    }

    public JobRequest1() {

    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public String getDuration() {
        return duration;
    }

    public String getDistance() {
        return distance;
    }

    public String getRider_id() {
        return rider_id;
    }

    public String getType() {
        return type;
    }

    public String getPickup_lat() {
        return pickup_lat;
    }

    public String getPickup_long() {
        return pickup_long;
    }

    public String getDrop_lat() {
        return drop_lat;
    }

    public String getDrop_long() {
        return drop_long;
    }

    public String getRide_fare() {
        return ride_fare;
    }
}
