package com.bluecup.hongyu.mocku2020.ui;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/22_下午7:37
 */
public interface ActivityHierarchyServer extends Application.ActivityLifecycleCallbacks {
    ActivityHierarchyServer NONE = new ActivityHierarchyServer() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };
}
