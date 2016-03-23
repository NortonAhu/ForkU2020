package com.bluecup.hongyu.mocku2020.data;

import android.content.Intent;
import android.net.Uri;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午2:03
 */
public interface IntentFactory {
    Intent createUrlIntent(String url);

    IntentFactory REAL = url -> {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    };
}
