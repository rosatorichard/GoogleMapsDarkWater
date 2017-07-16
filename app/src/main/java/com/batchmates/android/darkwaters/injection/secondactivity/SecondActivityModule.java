package com.batchmates.android.darkwaters.injection.secondactivity;

import com.batchmates.android.darkwaters.view.secondactivity.SecondActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android on 7/14/2017.
 */

@Module
public class SecondActivityModule {

    @Provides
    public SecondActivityPresenter secondActivityPresenter()
    {
        return new SecondActivityPresenter();
    }
}
