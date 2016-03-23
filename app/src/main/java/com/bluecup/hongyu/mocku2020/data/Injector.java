package com.bluecup.hongyu.mocku2020.data;

import android.content.Context;

import com.bluecup.hongyu.mocku2020.App;

import dagger.ObjectGraph;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午4:21
 */
public final class Injector {
    private static final String INJECTOR_SERVICE = "com.jakewharton.u2020.injector";

    public static ObjectGraph obtain(Context context) {
        return (ObjectGraph) ((App) context.getApplicationContext()).getSystemService(INJECTOR_SERVICE);
    }

    public static boolean matchesService(String name) {
        return INJECTOR_SERVICE.equals(name);
    }
}
