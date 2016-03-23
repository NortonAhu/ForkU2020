package com.bluecup.hongyu.mocku2020;

import android.app.Application;

import com.bluecup.hongyu.mocku2020.data.DataModule;
import com.bluecup.hongyu.mocku2020.ui.UiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/22_下午7:33
 */
@Module(
        injects = App.class,
        includes = {
                UiModule.class,
                DataModule.class
        }
)
public final class AppModule {
    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides @Singleton public Application provideApp() {
        return app;
    }
}
