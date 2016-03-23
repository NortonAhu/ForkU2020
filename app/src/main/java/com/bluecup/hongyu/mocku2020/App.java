package com.bluecup.hongyu.mocku2020;

import android.app.Application;

import com.bluecup.hongyu.mocku2020.data.LumberYard;
import com.jakewharton.threetenabp.AndroidThreeTen;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/22_下午7:28
 */
public class App extends Application {

    @Inject ActivityLifecycleCallbacks activityLifecycleCallbacks;
    @Inject
    LumberYard lumberYard;
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);

        objectGraph = ObjectGraph.create(Module.list(this));
        objectGraph.inject(this);
    }
}