package com.bluecup.hongyu.mocku2020.data.oauth;

import com.f2prateek.rx.preferences.Preference;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午2:27
 */
public final class OauthInterceptor implements Interceptor {

    private final Preference<String> accessToken;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (accessToken.isSet()) {
            builder.header("Authorization", "token " + accessToken.get());
        }
        return chain.proceed(builder.build());
    }

    @Inject
    public OauthInterceptor(@AccessToken Preference<String> accessToken) {
        this.accessToken = accessToken;
    }
}
