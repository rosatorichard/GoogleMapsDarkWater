package com.batchmates.android.darkwaters.view.secondactivity;

import com.batchmates.android.darkwaters.BasePresenter;
import com.batchmates.android.darkwaters.BaseView;
import com.batchmates.android.darkwaters.model.ClosePlaces;
import com.batchmates.android.darkwaters.model.YourShip;
import com.batchmates.android.darkwaters.view.MainActivityContract;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;

import java.util.List;

/**
 * Created by Android on 7/14/2017.
 */

public interface SecondActivityContract {

    interface View extends BaseView
    {
        public void getClosePlaces(List<ClosePlaces> listPlaces);
    }

    interface  Presenter extends BasePresenter<SecondActivityContract.View>
    {
        public void listOfNeerPlaces(PendingResult<PlaceLikelihoodBuffer> result);
        public void listOfNeerPlacesFood(PendingResult<PlaceLikelihoodBuffer> result);
        public void listOfNeerPlacesStore(PendingResult<PlaceLikelihoodBuffer> result);
    }
}
