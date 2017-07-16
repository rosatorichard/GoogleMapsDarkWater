package com.batchmates.android.darkwaters.injection.secondactivity;

import com.batchmates.android.darkwaters.view.secondactivity.MapsActivity;

import dagger.Component;

/**
 * Created by Android on 7/14/2017.
 */

@Component(modules = SecondActivityModule.class)
public interface SecondActivityComponent {
    void inject(MapsActivity mapsActivity);
}
