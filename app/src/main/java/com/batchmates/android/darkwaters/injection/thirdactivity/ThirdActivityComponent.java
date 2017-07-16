package com.batchmates.android.darkwaters.injection.thirdactivity;

import com.batchmates.android.darkwaters.view.thirdactivity.DisplayActivity;

import dagger.Component;

/**
 * Created by Android on 7/16/2017.
 */
@Component(modules = ThirdActivityModule.class)
public interface ThirdActivityComponent {
    void inject(DisplayActivity displayActivity);
}
