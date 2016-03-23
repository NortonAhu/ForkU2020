package com.bluecup.hongyu.mocku2020.ui;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午4:07
 */

@Module (
        injects = {MainActivity.class},
        complete = false,
        library = true

)
public final class UiModule {

    @Provides @Singleton ViewContainer provideContainer() {
        return ViewContainer.DEFAULT;
    }

    @Provides @Singleton ActivityHierarchyServer provideActivityHierarchyServer() {
        return ActivityHierarchyServer.NONE;
    }
}
