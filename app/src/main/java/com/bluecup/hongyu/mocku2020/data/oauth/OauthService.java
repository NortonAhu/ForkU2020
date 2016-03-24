package com.bluecup.hongyu.mocku2020.data.oauth;

import android.app.IntentService;
import android.content.Intent;

import com.bluecup.hongyu.mocku2020.data.Injector;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/24_上午10:54
 */
public class OauthService extends IntentService {
    @Inject OauthManager oauthManager;
    private ObjectGraph objectGraph;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public OauthService() {
        super(OauthService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        oauthManager.handleResult(intent.getData());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = Injector.obtain(getApplicationContext());
        objectGraph.inject(this);
    }
}
