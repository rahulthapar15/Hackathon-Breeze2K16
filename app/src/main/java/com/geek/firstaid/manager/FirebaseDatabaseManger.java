package com.geek.firstaid.manager;

import android.util.Log;

import com.geek.firstaid.models.User;
import com.geek.firstaid.utilities.PatientInfoBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shivang Goel on 25/6/16.
 */


public class FirebaseDatabaseManger {

    private String TAG = getClass().getSimpleName();
    private static FirebaseDatabaseManger mInstance;
    private FirebaseDatabase mDatabase;
    private String value;
    private boolean result;

    private FirebaseDatabaseManger() {
        mDatabase = FirebaseDatabase.getInstance();
    }

    public static FirebaseDatabaseManger getInstance() {
        if (mInstance == null)
            mInstance = new FirebaseDatabaseManger();
        return mInstance;
    }


    public void writeMapToDb(List<PatientInfoBean> infoBeanList) {
        DatabaseReference databaseReference = this.mDatabase.getReference();
        for (PatientInfoBean bean : infoBeanList) {
            String key = databaseReference.child("info").push().getKey();
            Map<String, Object> postValues = bean.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/info/" + key, postValues);
            databaseReference.updateChildren(childUpdates);
        }
    }

    public boolean checkLogin(final String uid) {
//        final String userId = getUid();
        DatabaseReference databaseReference = this.mDatabase.getReference();
        databaseReference.child("users").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            DataSnapshot snapshot = postSnapshot.child("uid");
                            Log.w(TAG, snapshot.getValue().toString());
                            if (uid.equalsIgnoreCase(snapshot.getValue().toString())) {
                                result = true;
                                break;
                            } else {
                                result = false;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled..........", databaseError.toException());
                        result = false;
                    }
                });
        return result;
    }

}
