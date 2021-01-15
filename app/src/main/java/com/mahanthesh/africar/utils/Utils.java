package com.mahanthesh.africar.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Location;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.maps.model.LatLng;
import com.mahanthesh.africar.model.LocationInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Utils {

    public static void closeKeyboard(Context context, View view) {
        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void saveLocalUser(Context context, String username, String email, String id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit()
                .putString(Constants.PREFERENCES_USER_NAME, username)
                .putString(Constants.PREFERENCES_USER_EMAIL, email)
                .putString(Constants.PREFERENCES_USER_ID, id)
                .apply();

    }

    public static Location getLocationFromLocationInfo(LocationInfo locationInfo, String name) {
        Location location = new Location(name);
        location.setLatitude(locationInfo.getLatitude());
        location.setLongitude(locationInfo.getLongitude());
        return location;
    }

    public static Location getLocationFromString(String latitude,String longitude, String name) {
        Location location = new Location(name);
        double lat = Double.parseDouble(latitude);
        double lon = Double.parseDouble(longitude);
        location.setLatitude(lat);
        location.setLongitude(lon);
        return location;
    }

    public static LatLng getLocationAsLatlngFromStr(String latitude, String longitude){
        double lat = Double.parseDouble(latitude);
        double lon = Double.parseDouble(longitude);
        return new LatLng(lat,lon);
    }

    public static double getLocationAsDoubleFromStr(String coordinate){
        double loc = Double.parseDouble(coordinate);
        return loc;
    }


    public static String getAddress(Address address) {
        StringBuilder builder = new StringBuilder();
        List<String> addressElements = new ArrayList<>();
        for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
            addressElements.add(address.getAddressLine(i));
        }
        builder.append(TextUtils.join(", ", addressElements));
        return builder.toString();


    }

    public static String getCurrentTime() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date currentTime = Calendar.getInstance().getTime();
        return fmt.format(currentTime);
    }

    public static double getDistance(Location startLocation, LocationInfo destinationLocation) {
        Location destLocation = new Location("destinationLocation");
        destLocation.setLongitude(destinationLocation.getLongitude());
        destLocation.setLatitude(destinationLocation.getLatitude());
        return startLocation.distanceTo(destLocation);
    }

    public static double getCustomDistance(Location startLocation, Location destinationLocation) {
        Location destLocation = new Location("destinationLocation");
        destLocation.setLongitude(destinationLocation.getLongitude());
        destLocation.setLatitude(destinationLocation.getLatitude());
        return startLocation.distanceTo(destLocation);
    }


    public static String getLocalUsername(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.PREFERENCES_USER_NAME, Constants.DEFAULT_USER);
    }

    public static String getLocalUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.PREFERENCES_USER_ID, Constants.DEFAULT_ID);
    }

    public static boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
}

