package com.bluecup.hongyu.mocku2020.data.api;

import com.bluecup.hongyu.mocku2020.data.oauth.OauthInterceptor;
import com.squareup.moshi.Moshi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午2:20
 */
public final class ApiModule {
    private static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("https://api.github.com/");

    @Provides HttpUrl provideBaseUrl() {
        return PRODUCTION_API_URL;
    }

    @Provides @Singleton @Named("Api") OkHttpClient provideApiClient(OkHttpClient okHttpClient, OauthInterceptor oauthInterceptor) {
        return createApiClient(okHttpClient, oauthInterceptor).build();
    }

    @Provides @Singleton Retrofit provideRetrofit(HttpUrl httpUrl, @Named("Api") OkHttpClient client, Moshi moshi) {
        return new Retrofit.Builder().client(client).baseUrl(httpUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides @Singleton GithubService provideGithubService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }


    static OkHttpClient.Builder createApiClient(OkHttpClient client, OauthInterceptor oauthInterceptor) {
        return client.newBuilder().addInterceptor(oauthInterceptor);
    }
}
