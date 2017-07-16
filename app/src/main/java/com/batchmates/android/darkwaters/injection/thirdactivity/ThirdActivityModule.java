package com.batchmates.android.darkwaters.injection.thirdactivity;

import com.batchmates.android.darkwaters.view.thirdactivity.ThirdActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android on 7/16/2017.
 */

@Module
public class ThirdActivityModule {

    @Provides
    public ThirdActivityPresenter thirdActivityPresenter()
    {
        return new ThirdActivityPresenter();
    }

}
