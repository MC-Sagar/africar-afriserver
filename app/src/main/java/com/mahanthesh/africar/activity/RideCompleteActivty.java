package com.mahanthesh.africar.activity;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mahanthesh.africar.R;
import com.mahanthesh.africar.model.JobRequest;
import com.mahanthesh.africar.model.JobRequest1;

import io.nlopez.smartlocation.SmartLocation;

import static com.mahanthesh.africar.utils.Utils.getAddress;
import static com.mahanthesh.africar.utils.Utils.getLocationFromString;

public class RideCompleteActivty extends AppCompatActivity {
    private Button completeButton;
    private JobRequest1 request;
    private TextView amountTextView, pickupTextView, dropTextView, timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_complete_activty);
        request = (JobRequest1) getIntent().getSerializableExtra("RideInfo");
        amountTextView = findViewById(R.id.amountTextView);
        pickupTextView = findViewById(R.id.pickupTextView);
        dropTextView = findViewById(R.id.dropTextView);
        timeTextView = findViewById(R.id.timeTextView);
        completeButton = findViewById(R.id.submitButton);
        String price = " " + request.getRide_fare();
        amountTextView.setText(price);
        Location pickupLocation = getLocationFromString(request.getPickup_lat(),request.getPickup_long(),"Pickup");
        Location dropLocation = getLocationFromString(request.getDrop_lat(),request.getDrop_long(), "Drop");
        SmartLocation.with(this).geocoding()
                .reverse(pickupLocation, (location, list) -> {
                    if (location.getProvider().equalsIgnoreCase("Pickup")) {
                        if (list.size() > 0) {
                            String pickUpAddress = getAddress(list.get(0));
                            pickupTextView.setText(pickUpAddress);
                        }

                    } else if (location.getProvider().equalsIgnoreCase("Drop")) {
                        if (list.size() > 0) {
                            String dropAddress = getAddress(list.get(0));
                            dropTextView.setText(dropAddress);
                        }
                    }

                });
        SmartLocation.with(this).geocoding()
                .reverse(dropLocation, (location, list) -> {
                    if (location.getProvider().equalsIgnoreCase("Pickup")) {
                        if (list.size() > 0) {
                            String pickUpAddress = getAddress(list.get(0));
                            pickupTextView.setText(pickUpAddress);
                        }

                    } else if (location.getProvider().equalsIgnoreCase("Drop")) {
                        if (list.size() > 0) {
                            String dropAddress = getAddress(list.get(0));
                            dropTextView.setText(dropAddress);
                        }
                    }

                });
        //timeTextView.setText(request.getAcceptedTime());
//        pickupTextView.setText("Pickup Location");
//        dropTextView.setText("Drop Location");
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // completed the ride and push the ride to another info
                finish();
            }
        });

    }
}