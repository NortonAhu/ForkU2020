package com.bluecup.hongyu.mocku2020.data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.bluecup.hongyu.mocku2020.data.api.ApiModule;
import com.bluecup.hongyu.mocku2020.data.oauth.AccessToken;
import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.jakewharton.byteunits.DecimalByteUnit;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

import org.threeten.bp.Clock;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_上午11:08
 */
@Module(
        includes = ApiModule.class,
        library = true,
        complete = false
)
public final class DataModule {
    static final int DISK_CACHE_SIZE = (int) DecimalByteUnit.MEGABYTES.toBytes(50);

    @Provides
    @Singleton
    public SharedPreferences provideSharePreference(Application app) {
        return app.getSharedPreferences("mocku2020", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public RxSharedPreferences provideRxSharedPreference(SharedPreferences sharedPreferences) {
        return RxSharedPreferences.create(sharedPreferences);
    }

    @Provides
    @Singleton
    @AccessToken
    Preference<String> provideAccessToken(RxSharedPreferences rxSharedPreferences) {
        return rxSharedPreferences.getString("access-token");
    }

    @Provides
    @Singleton
    Moshi provideMoshi() {
        return new Moshi.Builder().add(new InstantAdapter()).build();
    }

    @Provides
    @Singleton
    Clock provideClock() {
        return Clock.systemDefaultZone();
    }

    @Provides
    @Singleton
    IntentFactory provideIntentFactory() {
        return IntentFactory.REAL;
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Application app) {
        return createOkHttpClient(app).build();
    }

    @Provides
    @Singleton
    Picasso providePicasso(Application app, OkHttpClient okHttpClient) {
        return new Picasso.Builder(app).downloader(new OkHttp3Downloader(okHttpClient))
                .listener(((picasso, uri, exception) -> com.orhanobut.logger.Logger.e(exception, "Failed to load image: %s", uri)))
                .build();
    }

    static OkHttpClient.Builder createOkHttpClient(Application app) {
        File cacheDir = new File(app.getCacheDir(), "http");
        okhttp3.Cache cache = new okhttp3.Cache(cacheDir, DISK_CACHE_SIZE);
        return new OkHttpClient.Builder().cache(cache);
    }
}
