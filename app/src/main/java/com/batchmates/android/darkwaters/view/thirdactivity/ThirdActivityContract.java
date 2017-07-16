package com.batchmates.android.darkwaters.view.thirdactivity;

import android.widget.ImageView;
import android.widget.TextView;

import com.batchmates.android.darkwaters.BasePresenter;
import com.batchmates.android.darkwaters.BaseView;

import org.w3c.dom.Text;

/**
 * Created by Android on 7/16/2017.
 */

public interface ThirdActivityContract {

    interface View extends BaseView
    {

    }

    interface Presenter extends BasePresenter<ThirdActivityContract.View>
    {
        void setUpDisplay(ImageView imageView, TextView name, TextView address, TextView phone, TextView price, ImageView stars);
    }


}
