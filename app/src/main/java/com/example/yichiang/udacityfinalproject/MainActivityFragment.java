package com.example.yichiang.udacityfinalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.yichiang.udacityfinalproject.Models.FoodRequest;
import com.example.yichiang.udacityfinalproject.Services.FoodRequestServices;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    String TAG = "AutomComplete";
    private String selectedPlace;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        SupportPlaceAutocompleteFragment autocompleteFragment = (SupportPlaceAutocompleteFragment)
                getChildFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);


        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                selectedPlace = place.getAddress().toString();
                Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        Button button = (Button) view.findViewById(R.id.profile_btn_submit);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                FoodRequestServices foodRequestServices = new FoodRequestServices();
                FoodRequest newRequest = GetFoodRequestFormData(v);
                foodRequestServices.SaveFoodRequest(newRequest);
            }
        });

    }

    private FoodRequest GetFoodRequestFormData(View view){
        FoodRequest newRequest = new FoodRequest();
        EditText contactNameInput = (EditText) getView().findViewById(R.id.profile_et_contactName);
        EditText contactPhoneInput = (EditText) getView().findViewById(R.id.profile_et_phone);
        EditText descriptionInput = (EditText) getView().findViewById(R.id.profile_et_description);
        newRequest.RequesterName = contactNameInput.getText().toString();
        newRequest.RequesterPhone = contactPhoneInput.getText().toString();
        newRequest.PickupAddress = selectedPlace;
        newRequest.Description = descriptionInput.getText().toString();
        newRequest.Date = new Date();

        return newRequest;
    }



}
