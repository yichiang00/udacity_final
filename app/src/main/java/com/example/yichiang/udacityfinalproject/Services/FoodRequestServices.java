package com.example.yichiang.udacityfinalproject.Services;

import com.example.yichiang.udacityfinalproject.Models.FoodRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodRequestServices {
    public String FOOD_REQUEST_REF = "requests";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference requestRef = database.getReference(FOOD_REQUEST_REF);
    public Map<String, FoodRequest> allRequests = new HashMap <String, FoodRequest>();

    public FoodRequestServices(){
    }
    public void SaveFoodRequest(FoodRequest foodRequest){
        // Write a message to the database
        requestRef.push().setValue(foodRequest);
    }

    public Map<String, FoodRequest> GetAllRequest(){

        requestRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allRequests = (Map<String, FoodRequest>) dataSnapshot.getValue();

                System.out.println(allRequests);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        return allRequests;
    }
}
