package com.bluecup.hongyu.mocku2020.data.oauth;

import android.content.Intent;
import android.net.Uri;

import com.bluecup.hongyu.mocku2020.data.IntentFactory;
import com.f2prateek.rx.preferences.Preference;
import com.orhanobut.logger.Logger;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/24_上午10:55
 */
@Singleton
public final class OauthManager {

    private static final String CLIENT_ID = "5793abe5bcb6d90f0240";
    private static final String CLIENT_SECRET = "81a35659c60fc376629432a51fd81e5c66a8dace";
    private final IntentFactory intentFactory;
    private final OkHttpClient okHttpClient;
    private final Moshi moshi;
    private final Preference<String> accessToken;

    @Inject
    public OauthManager(IntentFactory intentFactory, OkHttpClient okHttpClient, Moshi moshi,
                        @AccessToken Preference<String> accessToken) {
        this.intentFactory = intentFactory;
        this.okHttpClient = okHttpClient;
        this.moshi = moshi;
        this.accessToken = accessToken;
    }

    public Intent createLoginIntent() {
        HttpUrl authorizeUrl = HttpUrl.parse("https://github.com/login/oauth/authorize")
                .newBuilder().addQueryParameter("client_id", CLIENT_ID).build();
        return intentFactory.createUrlIntent(authorizeUrl.toString());
    }

    public void handleResult(Uri data) {
        if (data == null) return;
        String code = data.getQueryParameter("code");
        if (code == null) return;

        try {
            Request request = new Request.Builder().
                    url("https://github.com/login/oauth/access_token").
                    post(new FormBody.Builder().add("client_id", CLIENT_ID)
                            .add("client_secret", CLIENT_SECRET)
                            .add("code", code).build()).build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                AccessTokenResponse accessTokenResponse = moshi.adapter(AccessTokenResponse.class).fromJson(response.body().string());
                if (accessTokenResponse != null && accessTokenResponse.access_token != null) {
                    accessToken.set(accessTokenResponse.access_token);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

            Logger.e(e, "accesstoken fail");
        }
    }

    private final static class AccessTokenResponse {
        private final String access_token;

        public AccessTokenResponse(String access_token, String scope) {
            this.access_token = access_token;
        }
    }
}
