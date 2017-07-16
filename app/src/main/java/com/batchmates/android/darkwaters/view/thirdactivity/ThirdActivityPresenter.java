package com.batchmates.android.darkwaters.view.thirdactivity;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Android on 7/16/2017.
 */

public class ThirdActivityPresenter implements ThirdActivityContract.Presenter{

    ThirdActivityContract.View view;
    @Override
    public void addview(ThirdActivityContract.View view) {
        this.view=view;
    }

    @Override
    public void removeView() {
        this.view=null;
    }

    @Override
    public void setUpDisplay(ImageView imageView, TextView name, TextView address, TextView phone, TextView price, ImageView stars) {

    }
}
