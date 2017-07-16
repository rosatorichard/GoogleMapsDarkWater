package com.batchmates.android.darkwaters;

/**
 * Created by Android on 7/13/2017.
 */

public interface BasePresenter<V extends BaseView >
{
    void addview(V view);

    void removeView();
}
