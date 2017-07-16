package com.batchmates.android.darkwaters.view.secondactivity;

import android.support.annotation.NonNull;
import android.util.Log;

import com.batchmates.android.darkwaters.model.ClosePlaces;
import com.batchmates.android.darkwaters.view.MainActivityContract;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 7/14/2017.
 */

public class SecondActivityPresenter implements SecondActivityContract.Presenter{

    SecondActivityContract.View view;
    List<ClosePlaces> listPlaces= new ArrayList<>();

    public void addview(SecondActivityContract.View view) {

        this.view=view;
    }

    @Override
    public void removeView() {
        this.view=null;
    }

    //all places in the area
    @Override
    public void listOfNeerPlaces(PendingResult<PlaceLikelihoodBuffer> result) {
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods) {

                listPlaces.clear();
                int i =0;
                int mMaxEntries=30;
                for (PlaceLikelihood placeLikelihood : placeLikelihoods) {
                    // Build a list of likely places to show the user. Max 5.
                    Log.d("Presenter", "onResult: Filling "+i);
//                    int test=placeLikelihood.getPlace().getPriceLevel();
//                    float test2=placeLikelihood.getPlace().getRating();
                    listPlaces.add(new ClosePlaces((String) placeLikelihood.getPlace().getName(),(String) placeLikelihood.getPlace().getAddress()
                            ,(String) placeLikelihood.getPlace().getAttributions(),placeLikelihood.getPlace().getLatLng(),placeLikelihood.getPlace().getPriceLevel(),
                            placeLikelihood.getPlace().getRating(), String.valueOf(placeLikelihood.getPlace().getPhoneNumber())));
                    i++;
                    if (i > (mMaxEntries - 1)) {
                        break;
                    }
                }
                view.getClosePlaces(listPlaces);
                placeLikelihoods.release();
            }

        });
    }

    //this is for only food options in the area
    @Override
    public void listOfNeerPlacesFood(PendingResult<PlaceLikelihoodBuffer> result) {
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods) {

                listPlaces.clear();
                int i =0;
                int mMaxEntries=30;
                List<Integer> types;
                boolean food=false;
                for (PlaceLikelihood placeLikelihood : placeLikelihoods) {
                    // Build a list of likely places to show the user. Max 5.
                    types=placeLikelihood.getPlace().getPlaceTypes();
                    for (int j = 0; j <types.size() ; j++) {
                        Log.d("Type", "onResult: "+types.get(j));
                        if(types.get(j)==Place.TYPE_FOOD)
                        {
                            Log.d("CurrentCount", "onResult: "+j);
                            food=true;
                        }
                    }
                    if(food==true) {
                        Log.d("Presenter", "onResult: Filling " + i);
                        listPlaces.add(new ClosePlaces((String) placeLikelihood.getPlace().getName(),(String) placeLikelihood.getPlace().getAddress()
                                ,(String) placeLikelihood.getPlace().getAttributions(),placeLikelihood.getPlace().getLatLng(),placeLikelihood.getPlace().getPriceLevel(),
                                placeLikelihood.getPlace().getRating(), String.valueOf(placeLikelihood.getPlace().getPhoneNumber())));
                        i++;
                        if (i > (mMaxEntries - 1)) {
                            break;
                        }
                        food=false;
                    }
                }
                view.getClosePlaces(listPlaces);
                placeLikelihoods.release();
            }

        });

    }


    //This is for only store options in the area
    @Override
    public void listOfNeerPlacesStore(PendingResult<PlaceLikelihoodBuffer> result) {
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods) {

                listPlaces.clear();
                int i =0;
                int mMaxEntries=30;
                List<Integer> types;
                boolean store=false;
                for (PlaceLikelihood placeLikelihood : placeLikelihoods) {
                    // Build a list of likely places to show the user. Max 5.

                    types=placeLikelihood.getPlace().getPlaceTypes();
                    for (int j = 0; j <types.size() ; j++) {
                        Log.d("Type", "onResult: "+types.get(j));
                        if(types.get(j)==Place.TYPE_STORE)
                        {
                            Log.d("CurrentCount", "onResult: "+j);
                            store=true;
                        }
                    }
                    if(store==true) {
                        Log.d("Presenter", "onResult: Filling " + i);
                        listPlaces.add(new ClosePlaces((String) placeLikelihood.getPlace().getName(),(String) placeLikelihood.getPlace().getAddress()
                                ,(String) placeLikelihood.getPlace().getAttributions(),placeLikelihood.getPlace().getLatLng(),placeLikelihood.getPlace().getPriceLevel(),
                                placeLikelihood.getPlace().getRating(), String.valueOf(placeLikelihood.getPlace().getPhoneNumber())));
                        i++;
                        if (i > (mMaxEntries - 1)) {
                            break;
                        }
                        store=false;
                    }
                }
                view.getClosePlaces(listPlaces);
                placeLikelihoods.release();
            }

        });

    }
}
