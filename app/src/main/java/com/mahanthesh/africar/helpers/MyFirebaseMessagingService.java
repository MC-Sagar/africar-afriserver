package com.mahanthesh.africar.helpers;



import android.util.Log;

import androidx.annotation.NonNull;

import com.donkey.dolly.Dolly;
import com.donkey.dolly.Type;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mahanthesh.africar.model.JobRequest1;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.Map;

import static android.content.ContentValues.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    Dolly dolly;

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

       dolly =  Dolly.getInstance(getApplicationContext());
       dolly.putString("FCMToken",s, Type.NOT_ENCRYPTED);

        Log.d(TAG, "onNewToken: "+ s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getData().size() > 0){
            try{
                Log.d(TAG, "onMessageReceived: " + remoteMessage.getData());
                Map<String, String> params = remoteMessage.getData();
                JSONObject object = new JSONObject(params);
                String duration = object.getString("duration");
                String distance = object.getString("distance");
                String rider_id = object.getString("rider_id");
                JSONObject pickupObj = new JSONObject(object.getString("pickup"));
                JSONObject dropObj = new JSONObject(object.getString("drop"));
                String pickup_lat = pickupObj.getString("lat");
                String pickup_long = pickupObj.getString("long");
                String drop_lat = dropObj.getString("lat");
                String drop_long = dropObj.getString("long");
                String fare = object.getString("fare");
                String type = object.getString("type");
                JobRequest1 jobRequest1 = new JobRequest1(duration,distance,rider_id,type,pickup_lat,pickup_long,drop_lat,drop_long,fare);

                EventBus.getDefault().post(jobRequest1);

            }catch(Exception e){
                Log.e(TAG, "onMessageReceived: ",e );
            }


        }

        if(remoteMessage.getNotification() != null){
            Log.d(TAG, "onMessageReceived: getNotfication: " + remoteMessage.getNotification().getBody());
        }

    }



}
