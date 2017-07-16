package com.batchmates.android.darkwaters.view;

import com.batchmates.android.darkwaters.model.YourShip;

/**
 * Created by Android on 7/13/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter{


    MainActivityContract.View view;

    @Override
    public void addview(MainActivityContract.View view) {
        this.view=view;
    }

    @Override
    public void removeView() {
        this.view=null;
    }

    @Override
    public void setUpCaptain(String shipName, String captainName) {
        YourShip yourShip= new YourShip(shipName,captainName,"Wood plank",0,0);
        view.readyToSetSail(yourShip);
    }
}
