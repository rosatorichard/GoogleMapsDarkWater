package com.batchmates.android.darkwaters.view;

import com.batchmates.android.darkwaters.BasePresenter;
import com.batchmates.android.darkwaters.BaseView;
import com.batchmates.android.darkwaters.model.YourShip;

import java.util.List;

/**
 * Created by Android on 7/13/2017.
 */

public interface MainActivityContract {

    interface View extends BaseView
    {
        void setFullName(String fullName);
        void readyToSetSail(YourShip yourShip);

    }

    interface  Presenter extends BasePresenter<View>
    {
        void setUpCaptain(String shipName, String captainName);
    }
}
