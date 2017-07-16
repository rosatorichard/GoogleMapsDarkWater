package com.batchmates.android.darkwaters.injection.mainactivity;

import com.batchmates.android.darkwaters.view.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android on 7/13/2017.
 */
@Module
public class MainActivityModule {

    @Provides
    public MainActivityPresenter provideMainActivityPresenter()
    {
        return new MainActivityPresenter();
    }
}

