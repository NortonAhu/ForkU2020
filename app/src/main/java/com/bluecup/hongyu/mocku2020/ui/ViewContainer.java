package com.bluecup.hongyu.mocku2020.ui;

import android.app.Activity;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
  Des:
 * Created by hongyu
 * Date:16/3/23_下午4:03
 */
public interface ViewContainer {
    ViewGroup forActivity(Activity activity);

    ViewContainer DEFAULT = activity -> ButterKnife.findById(activity, android.R.id.content);
}
