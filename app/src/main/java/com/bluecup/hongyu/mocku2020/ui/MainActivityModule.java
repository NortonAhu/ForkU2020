package com.bluecup.hongyu.mocku2020.ui;

import android.support.v4.widget.DrawerLayout;

import com.bluecup.hongyu.mocku2020.AppModule;
import com.bluecup.hongyu.mocku2020.ui.trending.TrendingView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午4:13
 */
@Module (
        addsTo = AppModule.class,
        injects = TrendingView.class
)
public final class MainActivityModule {
    private final MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides @Singleton DrawerLayout provideDrawrLayout() {
        return null;
    }

}
