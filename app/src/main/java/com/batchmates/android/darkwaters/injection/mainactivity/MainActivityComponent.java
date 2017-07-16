package com.batchmates.android.darkwaters.injection.mainactivity;

import com.batchmates.android.darkwaters.view.MainActivity;

import dagger.Component;

/**
 * Created by Android on 7/13/2017.
 */
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
