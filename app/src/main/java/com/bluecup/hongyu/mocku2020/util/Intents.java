package com.bluecup.hongyu.mocku2020.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.widget.Toast;

import com.bluecup.hongyu.mocku2020.R;

import java.util.List;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/25_上午11:38
 */
public final class Intents {

    public static boolean maybeStartActivity(Context context, Intent intent) {
        return maybeStartActivity(context, intent, false);
    }

    public static boolean maybeStartChosser(Context context, Intent intent) {
        return maybeStartActivity(context, intent, true);
    }

    private static boolean maybeStartActivity(Context context, Intent intent, boolean chooser) {
        if (hasHandler(context, intent)) {
            if (chooser) {
                intent = Intent.createChooser(intent, null);
            }
            context.startActivity(intent);
            return true;
        } else {
            Toast.makeText(context, R.string.no_intent_handler, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private static boolean hasHandler(Context context, Intent intent) {
        List<ResolveInfo> handler = context.getPackageManager().queryIntentActivities(intent, 0);
        return !handler.isEmpty();
    }

    private Intents() {
        throw new AssertionError("no instance");
    }
}
